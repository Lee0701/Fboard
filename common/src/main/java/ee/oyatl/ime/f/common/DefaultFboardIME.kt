package ee.oyatl.ime.f.common

import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.inputmethodservice.InputMethodService
import android.os.Build
import android.view.KeyCharacterMap
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import ee.oyatl.ime.f.common.table.MoreKeysTable
import ee.oyatl.ime.f.common.switcher.IMESwitcher
import ee.oyatl.ime.f.common.switcher.InAppIMESwitcher
import ee.oyatl.ime.f.common.switcher.LegacyIMESwitcher
import ee.oyatl.ime.f.common.switcher.NewIMESwitcher
import ee.oyatl.ime.f.common.view.DefaultInputViewManager
import ee.oyatl.ime.f.common.view.InputViewManager
import ee.oyatl.ime.f.common.view.keyboard.FlickDirection
import ee.oyatl.ime.f.common.view.keyboard.KeyboardListener
import ee.oyatl.ime.f.core.input.ModifierState
import ee.oyatl.ime.f.core.table.CharOverrideTable
import ee.oyatl.ime.f.core.table.CodeConvertTable

abstract class DefaultFboardIME: InputMethodService(), FboardIME, KeyboardListener {
    protected val pref: SharedPreferences get() = PreferenceManager.getDefaultSharedPreferences(this)

    val inputViewManager: InputViewManager get() = inAppIMESwitcher.currentState.inputViewManager
    val moreKeysTable: MoreKeysTable get() = inAppIMESwitcher.currentState.moreKeysTable
    val convertTable: CodeConvertTable get() = inAppIMESwitcher.currentState.convertTable
    val overrideTable: CharOverrideTable get() = inAppIMESwitcher.currentState.overrideTable

    protected val keyCharacterMap: KeyCharacterMap = KeyCharacterMap.load(KeyCharacterMap.VIRTUAL_KEYBOARD)

    private val doubleTapGap: Int = 500

    protected var modifierState: ModifierState = ModifierState()
    protected var shiftClickedTime: Long = 0
    protected var inputRecorded: Boolean = false

    protected val imeSwitcher: IMESwitcher by lazy {
        when(Build.VERSION.SDK_INT) {
            in 0 until Build.VERSION_CODES.P -> LegacyIMESwitcher(this)
            else -> NewIMESwitcher(this)
        }
    }
    protected val inAppIMESwitcher: InAppIMESwitcher by lazy {
        InAppIMESwitcher()
    }

    override fun onUpdate() {
        updateLabelsAndIcons()
        updateMoreKeys()
    }
    override fun onLanguageKey(): Boolean {
        return imeSwitcher.next()
    }
    override fun onSymbolsKey(): Boolean {
        val result = inAppIMESwitcher.next()
        setInputView(inputViewManager.createView(this))
        return result
    }

    open fun onShiftKeyDown() {
        val lastState = modifierState
        val lastShiftState = lastState.shiftState
        val currentShiftState = lastShiftState.copy()
        val newShiftState = currentShiftState.copy()

        modifierState = lastState.copy(shiftState = newShiftState.copy(pressing = true))
        inputRecorded = false
        onUpdate()
    }

    open fun onShiftKeyUp() {
        val lastState = modifierState
        val lastShiftState = lastState.shiftState
        val currentShiftState = lastShiftState.copy(pressing = false)

        val currentTime = System.currentTimeMillis()
        val timeDiff = currentTime - shiftClickedTime

        val newShiftState = if(currentShiftState.locked) {
            ModifierState.Item()
        } else if(currentShiftState.pressed) {
            if(timeDiff < doubleTapGap) {
                ModifierState.Item(pressed = true, locked = true)
            } else {
                ModifierState.Item()
            }
        } else if(inputRecorded) {
            ModifierState.Item()
        } else {
            ModifierState.Item(pressed = true)
        }

        modifierState = lastState.copy(shiftState = newShiftState.copy(pressing = false))
        shiftClickedTime = currentTime
        inputRecorded = false
        onUpdate()
    }

    override fun onCreate() {
        super<InputMethodService>.onCreate()
    }

    override fun onCreateInputView(): View? {
        val view = inputViewManager.createView(this)
        this.onUpdate()
        return view
    }

    override fun onStartInput(attribute: EditorInfo?, restarting: Boolean) {
        super.onStartInput(attribute, restarting)
        this.onReset()
        this.onUpdate()
    }

    override fun onDestroy() {
        super<InputMethodService>.onDestroy()
    }

    protected open fun updateLabelsAndIcons() {
        val labelsToUpdate = KeyEvent.KEYCODE_UNKNOWN .. KeyEvent.KEYCODE_SEARCH
        val labels = labelsToUpdate.associateWith { code ->
            keyCharacterMap.get(code, modifierState.asMetaState()).toChar().toString()
        }
        val icons = mapOf<Int, Drawable>()
        inputViewManager.updateLabelsAndIcons(labels, getIcons() + icons)
    }

    protected open fun updateMoreKeys() {
        val inputViewManager = inputViewManager
        if(inputViewManager is DefaultInputViewManager) {
            val moreKeysTable = this.moreKeysTable.map.map { (char, value) ->
                val keyCode: Int? = convertTable.getReversed(char, modifierState)
                if(keyCode != null) keyCode to value else null
            }.filterNotNull().toMap()
            inputViewManager.keyboardView?.updateMoreKeyKeyboards(moreKeysTable)
        }
    }

    private fun getIcons(): Map<Int, Drawable> {
        val shiftIconID = if(modifierState.shiftState.locked) R.drawable.keyic_shift_lock else R.drawable.keyic_shift
        val shiftIcon = ContextCompat.getDrawable(this, shiftIconID)
        val icons = shiftIcon?.let { mapOf(KeyEvent.KEYCODE_SHIFT_LEFT to it, KeyEvent.KEYCODE_SHIFT_RIGHT to it) }.orEmpty()
        return icons
    }

    override fun onKeyClick(code: Int, output: String?) {
        val isPrintingKey = codeIsPrintingKey(code)
        val consumed: Boolean = if(isPrintingKey) {
            onPrintingKey(code)
        } else when(code) {
            KeyEvent.KEYCODE_DEL -> onDeleteKey()
            KeyEvent.KEYCODE_SPACE -> onSpace()
            KeyEvent.KEYCODE_ENTER -> onActionKey()
            KeyEvent.KEYCODE_LANGUAGE_SWITCH -> onLanguageKey()
            KeyEvent.KEYCODE_SYM -> onSymbolsKey()
            KeyEvent.KEYCODE_SHIFT_LEFT, KeyEvent.KEYCODE_SHIFT_RIGHT -> true
            else -> false
        }
        if(!consumed) {
            val inputConnection = currentInputConnection ?: return
            if(code == 0 && output != null) {
                inputConnection.commitText(output, 1)
            } else if(isPrintingKey) {
                this.onReset()
                val char = keyCharacterMap.get(code, modifierState.asMetaState())
                if(char > 0) inputConnection.commitText(char.toChar().toString(), 1)
            } else {
                sendDownUpKeyEvents(code)
            }
        }
        if(isPrintingKey) {
            inputRecorded = true
        }
        this.autoUnshift()
        this.onUpdate()
    }

    override fun onKeyLongClick(code: Int, output: String?) {
    }

    override fun onKeyDown(code: Int, output: String?) {
        when(code) {
            KeyEvent.KEYCODE_SHIFT_LEFT, KeyEvent.KEYCODE_SHIFT_RIGHT -> onShiftKeyDown()
        }
    }

    override fun onKeyUp(code: Int, output: String?) {
        when(code) {
            KeyEvent.KEYCODE_SHIFT_LEFT, KeyEvent.KEYCODE_SHIFT_RIGHT -> onShiftKeyUp()
        }
    }

    override fun onKeyFlick(direction: FlickDirection, code: Int, output: String?) {
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when(keyCode) {
            KeyEvent.KEYCODE_SHIFT_LEFT, KeyEvent.KEYCODE_SHIFT_RIGHT -> {
                modifierState = modifierState.copy(shiftState = ModifierState.Item(
                    pressed = true,
                    pressing = true,
                ))
                onUpdate()
            }
            KeyEvent.KEYCODE_CAPS_LOCK -> {
                val newState = !modifierState.shiftState.locked
                modifierState = modifierState.copy(shiftState = ModifierState.Item(
                    pressed = newState,
                    locked = newState,
                ))
                onUpdate()
            }
            else -> {
                this.onKeyClick(keyCode, event?.unicodeChar?.toChar()?.toString())
            }
        }
        return true
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        when(keyCode) {
            KeyEvent.KEYCODE_SHIFT_LEFT, KeyEvent.KEYCODE_SHIFT_RIGHT -> {
                modifierState = modifierState.copy(shiftState = ModifierState.Item(
                    pressed = false,
                    pressing = false,
                ))
                onUpdate()
            }
        }
        return true
    }

    protected open fun autoUnshift() {
        if(modifierState.shiftState.pressing && inputRecorded) return
        if(inAppIMESwitcher.currentStateName == "symbols") return
        val lastState = modifierState
        val lastShiftState = lastState.shiftState
        if(!lastShiftState.locked && !lastShiftState.pressing) {
            modifierState = lastState.copy(shiftState = ModifierState.Item())
        }
    }

    override fun onComputeInsets(outInsets: Insets?) {
        super.onComputeInsets(outInsets)
        if(outInsets == null) return
        outInsets.contentTopInsets = outInsets.visibleTopInsets
    }

    companion object {
        fun codeIsPrintingKey(keyCode: Int): Boolean = KeyEvent(KeyEvent.ACTION_DOWN, keyCode).isPrintingKey
    }
}