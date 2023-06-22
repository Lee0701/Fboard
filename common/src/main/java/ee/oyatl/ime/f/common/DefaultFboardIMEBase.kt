package ee.oyatl.ime.f.common

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.provider.Settings
import android.view.KeyCharacterMap
import android.view.KeyEvent
import android.view.View
import ee.oyatl.ime.f.common.view.DefaultInputViewManager
import ee.oyatl.ime.f.common.view.InputViewManager
import ee.oyatl.ime.f.common.view.keyboard.FlickDirection
import ee.oyatl.ime.f.common.view.keyboard.KeyboardListener

abstract class DefaultFboardIMEBase(
    final override val params: InputViewManager.Params,
): FboardIMEBase(), InputViewManager, KeyboardListener, IMESwitcher {

    val doubleTapGap: Int = 500

    protected var modifierState: ModifierState = ModifierState()
    protected var shiftClickedTime: Long = 0
    protected var inputExists: Boolean = false

    protected val keyCharacterMap: KeyCharacterMap = KeyCharacterMap.load(KeyCharacterMap.VIRTUAL_KEYBOARD)
    final override val keyboardListener: KeyboardListener = this

    open val inputViewManager: InputViewManager =
        DefaultInputViewManager(keyboardListener, params)

    protected val imeSwitcher: IMESwitcher = when(Build.VERSION.SDK_INT) {
        in 0 until Build.VERSION_CODES.P -> LegacyIMESwitcher(this)
        else -> this
    }

    open fun onUpdate() = Unit
    open fun onReset() = Unit
    open fun onPrintingKey(keyCode: Int): Boolean = false
    open fun onDeleteKey(): Boolean = false
    open fun onSpace(): Boolean = false
    open fun onActionKey(): Boolean = false
    private fun onLanguageKey(): Boolean {
        return imeSwitcher.next()
    }

    open fun onShiftKeyDown() {
        val lastState = modifierState
        val lastShiftState = lastState.shiftState
        val currentShiftState = lastShiftState.copy()
        val newShiftState = currentShiftState.copy()

        modifierState = lastState.copy(shiftState = newShiftState.copy(pressing = true))
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
        } else if(inputExists) {
            ModifierState.Item()
        } else {
            ModifierState.Item(pressed = true)
        }

        modifierState = lastState.copy(shiftState = newShiftState.copy(pressing = false))
        shiftClickedTime = currentTime
        inputExists = false
        onUpdate()
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onCreateInputView(): View {
        val view = this.createView(this)
        this.onUpdate()
        return view
    }

    override fun createView(context: Context): View {
        return inputViewManager.createView(context)
    }

    override fun updateLabelsAndIcons(labels: Map<Int, CharSequence>, icons: Map<Int, Drawable>) {
        inputViewManager.updateLabelsAndIcons(labels, icons)
    }

    override fun onKeyClick(code: Int, output: String?) {
        val consumed: Boolean = if(isPrintingKey(code)) {
            onPrintingKey(code)
        } else when(code) {
            KeyEvent.KEYCODE_DEL -> onDeleteKey()
            KeyEvent.KEYCODE_SPACE -> onSpace()
            KeyEvent.KEYCODE_ENTER -> onActionKey()
            KeyEvent.KEYCODE_LANGUAGE_SWITCH -> onLanguageKey()
            KeyEvent.KEYCODE_SHIFT_LEFT, KeyEvent.KEYCODE_SHIFT_RIGHT -> true
            else -> false
        }
        if(!consumed) {
            val inputConnection = currentInputConnection ?: return
            if(modifierState.shiftState.pressed)
                inputConnection.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_SHIFT_LEFT))
            sendDownUpKeyEvents(code)
            if(modifierState.shiftState.pressed)
                inputConnection.sendKeyEvent(KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_SHIFT_LEFT))
        }
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

    private fun isPrintingKey(code: Int): Boolean = KeyEvent(KeyEvent.ACTION_DOWN, code).isPrintingKey

    override fun current(): String {
        return Settings.Secure.getString(contentResolver, Settings.Secure.DEFAULT_INPUT_METHOD)
    }

}