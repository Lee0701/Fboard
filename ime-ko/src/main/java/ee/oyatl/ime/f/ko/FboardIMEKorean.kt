package ee.oyatl.ime.f.ko

import android.graphics.drawable.Drawable
import android.view.KeyEvent
import ee.oyatl.ime.f.common.DefaultFboardIMEBase
import ee.oyatl.ime.f.core.table.CharOverrideTable
import ee.oyatl.ime.f.core.table.LayeredCodeConvertTable
import ee.oyatl.ime.f.ko.data.HangulTables
import ee.oyatl.ime.f.ko.hangul.HangulCombiner

class FboardIMEKorean: DefaultFboardIMEBase() {

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
        val inputConnection = currentInputConnection ?: return
        val state = modifierState
        val labelsRange = KeyEvent.KEYCODE_UNKNOWN .. KeyEvent.KEYCODE_SEARCH
        val defaultLabels = labelsRange.associateWith { code ->
            keyCharacterMap.get(code, state.asMetaState()).toChar().toString()
        }
        val tableLabels = convertTable
            .getAllForState(state)
            .mapValues { (key, value) -> value.toChar().toString() }
        val icons = mapOf<Int, Drawable>()
        updateLabelsAndIcons(
            labels = defaultLabels + tableLabels,
            icons = icons,
        )
        inputConnection.setComposingText(hangulState.composed, 1)
    }

    override fun onPrintingKey(keyCode: Int): Boolean {
        val inputConnection = currentInputConnection ?: return false
        val state = modifierState
        val converted =
            if(convertTable is LayeredCodeConvertTable) convertTable.get(layerIdByHangulState, keyCode, state)
            else convertTable.get(keyCode, state)
        if(converted == null) {
            val char = keyCharacterMap.get(keyCode, state.asMetaState())
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