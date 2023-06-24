package ee.oyatl.ime.f.sym

import android.graphics.drawable.Drawable
import ee.oyatl.ime.f.common.DefaultFboardIME
import ee.oyatl.ime.f.common.TableIME
import ee.oyatl.ime.f.common.layouts.SoftKeyboardLayouts
import ee.oyatl.ime.f.common.view.model.KeyboardLayout
import ee.oyatl.ime.f.core.table.CharOverrideTable
import ee.oyatl.ime.f.sym.data.SymbolTables

class FboardIMESymbols: DefaultFboardIME(), TableIME {

    override val keyboardLayout: KeyboardLayout = SoftKeyboardLayouts.LAYOUT_QWERTY_MOBILE_SEMICOLON
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