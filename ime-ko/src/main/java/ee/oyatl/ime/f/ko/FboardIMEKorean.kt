package ee.oyatl.ime.f.ko

import ee.oyatl.ime.f.common.DefaultFboardIME
import ee.oyatl.ime.f.common.TableIME
import ee.oyatl.ime.f.common.layouts.SoftKeyboardLayouts
import ee.oyatl.ime.f.common.table.MoreKeysTable
import ee.oyatl.ime.f.common.view.model.KeyboardLayout
import ee.oyatl.ime.f.core.table.CharOverrideTable
import ee.oyatl.ime.f.core.table.LayeredCodeConvertTable
import ee.oyatl.ime.f.ko.data.HangulTables
import ee.oyatl.ime.f.ko.hangul.HangulCombiner

class FboardIMEKorean: DefaultFboardIME(), TableIME {

    override val keyboardLayout: KeyboardLayout = SoftKeyboardLayouts.LAYOUT_QWERTY_MOBILE
    override val moreKeysTable: MoreKeysTable = MoreKeysTable()
    override val convertTable = HangulTables.LAYOUT_2SET_KS
    override val overrideTable: CharOverrideTable = CharOverrideTable()
    private val jamoCombinationTable = HangulTables.COMB_2SET_STANDARD
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
        super.onUpdate()
        val inputConnection = currentInputConnection ?: return
        inputConnection.setComposingText(hangulState.composed, 1)
    }

    override fun updateLabelsAndIcons() {
        super.updateLabelsAndIcons()
        val labels = convertTable.getAllForState(modifierState).map { (k, v) ->
            k to v.toChar().toString()
        }.toMap()
        inputViewManager.updateLabelsAndIcons(labels, mapOf())
    }

    override fun onPrintingKey(keyCode: Int): Boolean {
        val inputConnection = currentInputConnection ?: return false
        val converted =
            if(convertTable is LayeredCodeConvertTable) convertTable.get(layerIdByHangulState, keyCode, modifierState)
            else convertTable[keyCode, modifierState]
        if(converted == null) {
            val char = keyCharacterMap.get(keyCode, modifierState.asMetaState())
            onReset()
            if(char > 0) inputConnection.commitText(char.toChar().toString(), 1)
        } else {
            val override = overrideTable[converted]
            val (text, newStates) =
                hangulCombiner.combine(hangulState, override ?: converted)
            if(text.isNotEmpty()) {
                stateStack.clear()
                inputConnection.commitText(text, 1)
            }
            this.stateStack += newStates
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

    override fun onSpace(): Boolean {
        onReset()
        return super.onSpace()
    }

    override fun onActionKey(): Boolean {
        onReset()
        return super.onActionKey()
    }

}