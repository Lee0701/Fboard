package ee.oyatl.ime.f.sym

import android.graphics.drawable.Drawable
import ee.oyatl.ime.f.common.DefaultTableIME
import ee.oyatl.ime.f.common.layouts.SoftKeyboardLayouts
import ee.oyatl.ime.f.common.table.MoreKeysTable
import ee.oyatl.ime.f.common.view.model.KeyboardLayout
import ee.oyatl.ime.f.core.table.CharOverrideTable
import ee.oyatl.ime.f.sym.data.MoreKeysTables
import ee.oyatl.ime.f.sym.data.SymbolTables

class FboardIMESymbols: DefaultTableIME() {

    override val keyboardLayout: KeyboardLayout = SoftKeyboardLayouts.LAYOUT_QWERTY_MOBILE_SEMICOLON
    override val moreKeysTable: MoreKeysTable = MoreKeysTables.MORE_KEYS_G
    override val convertTable = SymbolTables.LAYOUT_SYMBOLS_G
    override val overrideTable: CharOverrideTable = CharOverrideTable()

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