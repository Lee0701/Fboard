package ee.oyatl.ime.f.en

import ee.oyatl.ime.f.common.DefaultTableIME
import ee.oyatl.ime.f.common.layouts.SoftKeyboardLayouts
import ee.oyatl.ime.f.common.table.MoreKeysTable
import ee.oyatl.ime.f.common.view.model.KeyboardLayout
import ee.oyatl.ime.f.core.table.CharOverrideTable
import ee.oyatl.ime.f.core.table.CodeConvertTable
import ee.oyatl.ime.f.core.table.SimpleCodeConvertTable

class FboardIMEEnglish: DefaultTableIME() {

    override val keyboardLayout: KeyboardLayout = SoftKeyboardLayouts.LAYOUT_QWERTY_MOBILE
    override val moreKeysTable: MoreKeysTable = MoreKeysTable()
    override val overrideTable: CharOverrideTable = CharOverrideTable()
    override val convertTable: CodeConvertTable = SimpleCodeConvertTable()

    override fun updateLabelsAndIcons() {
        super.updateLabelsAndIcons()
    }

    override fun onPrintingKey(keyCode: Int): Boolean {
        val inputConnection = currentInputConnection ?: return super.onPrintingKey(keyCode)
        val converted = convertTable[keyCode, modifierState]
        if(converted == null) {
            return super.onPrintingKey(keyCode)
        } else {
            val override = overrideTable[converted]
            val text = (override ?: converted).toChar().toString()
            inputConnection.commitText(text, 1)
            return true
        }
    }

}