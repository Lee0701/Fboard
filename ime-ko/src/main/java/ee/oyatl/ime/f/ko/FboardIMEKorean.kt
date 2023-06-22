package ee.oyatl.ime.f.ko

import android.graphics.drawable.Drawable
import android.view.KeyCharacterMap
import ee.oyatl.ime.f.common.input.KeyboardState
import ee.oyatl.ime.f.common.input.LayeredCodeConvertTable
import ee.oyatl.ime.f.common.view.DefaultFboardIMEBase
import ee.oyatl.ime.f.common.view.data.SoftKeyboardLayouts
import ee.oyatl.ime.f.common.view.InputViewManager
import ee.oyatl.ime.f.common.view.keyboard.Themes
import ee.oyatl.ime.f.ko.data.HangulTables
import ee.oyatl.ime.f.ko.hangul.HangulCombiner

class FboardIMEKorean: DefaultFboardIMEBase(
    params = generateInputViewParams(),
) {
    private val convertTable = HangulTables.LAYOUT_2SET_KS
    private val jamoCombinationTable = HangulTables.COMB_2SET_STANDARD

    private val keyCharacterMap: KeyCharacterMap = KeyCharacterMap.load(KeyCharacterMap.VIRTUAL_KEYBOARD)
    private val hangulCombiner = HangulCombiner(jamoCombinationTable)

    private val stateStack: MutableList<HangulCombiner.State> = mutableListOf()
    private val hangulState: HangulCombiner.State get() = stateStack.lastOrNull() ?: HangulCombiner.State()
    private val layerIdByHangulState: String get() {
        val cho = hangulState.cho
        val jung = hangulState.jung
        val jong = hangulState.jong

        return if(jong != null && jong and 0xff00000 == 0) "\$jong"
        else if(jung != null && jung and 0xff00000 == 0) "\$jung"
        else if(cho != null && cho and 0xff00000 == 0) "\$cho"
        else "base"
    }

    override fun onReset() {
        currentInputConnection?.finishComposingText()
        stateStack.clear()
    }

    override fun onUpdate() {
        val inputConnection = currentInputConnection ?: return
        val state = KeyboardState()
        val labels = convertTable
            .getAllForState(state)
            .mapValues { (key, value) -> value.toChar().toString() }
        val icons = mapOf<Int, Drawable>()
        inputViewManager.updateLabelsAndIcons(
            labels = labels,
            icons = icons,
        )
        inputConnection.setComposingText(hangulState.composed, 1)
    }

    override fun onPrintingKey(keyCode: Int): Boolean {
        val inputConnection = currentInputConnection ?: return false
        val state = KeyboardState()
        val converted =
            if(convertTable is LayeredCodeConvertTable) convertTable.get(layerIdByHangulState, keyCode, state)
            else convertTable.get(keyCode, state)
        if(converted == null) {
            val char = keyCharacterMap.get(keyCode, state.asMetaState())
            onReset()
            if(char > 0) inputConnection.commitText(char.toChar().toString(), 1)
        } else {
            val (text, newStates) =
                hangulCombiner.combine(hangulState, converted)
            if(text.isNotEmpty()) stateStack.clear()
            this.stateStack += newStates
            inputConnection.commitText(text, 1)
        }
        onUpdate()
        return true
    }

    override fun onDeleteKey(): Boolean {
        val inputConnection = currentInputConnection ?: return false
        if(stateStack.size >= 1) {
            stateStack.removeLastOrNull()
        } else {
            inputConnection.deleteSurroundingText(1, 0)
        }
        onUpdate()
        return true
    }

    companion object {
        fun generateInputViewParams(): InputViewManager.Params = InputViewManager.Params(
            keyboardLayout = SoftKeyboardLayouts.LAYOUT_QWERTY_MOBILE,
            keyboardTheme = Themes.Dynamic,
            unifyHeight = false,
            rowHeight = 54,
        )
    }
}