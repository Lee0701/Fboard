package ee.oyatl.ime.f.sym

import android.graphics.drawable.Drawable
import ee.oyatl.ime.f.common.DefaultFboardIMEBase
import ee.oyatl.ime.f.core.table.CharOverrideTable
import ee.oyatl.ime.f.sym.data.SymbolTables

class FboardIMESymbols: DefaultFboardIMEBase() {

    override val convertTable = SymbolTables.LAYOUT_SYMBOLS_G
    override val overrideTable: CharOverrideTable = CharOverrideTable()

    override fun onUpdate() {
        val inputConnection = currentInputConnection ?: return
        val state = modifierState
        val labels = convertTable
            .getAllForState(state)
            .mapValues { (key, value) -> value.toChar().toString() }
        val icons = mapOf<Int, Drawable>()
        updateLabelsAndIcons(
            labels = labels,
            icons = icons,
        )
    }

    override fun onPrintingKey(keyCode: Int): Boolean {
        val inputConnection = currentInputConnection ?: return false
        val state = modifierState
        val converted = convertTable[keyCode, state]
        if(converted == null) {
            val char = keyCharacterMap.get(keyCode, state.asMetaState())
            onReset()
            if(char > 0) inputConnection.commitText(char.toChar().toString(), 1)
        } else {
            val override = overrideTable[converted]
            val text = (override ?: converted).toChar().toString()
            inputConnection.commitText(text, 1)
        }
        onUpdate()
        return true
    }

}