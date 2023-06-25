package ee.oyatl.ime.f.en

import ee.oyatl.ime.f.common.DefaultTableIME
import ee.oyatl.ime.f.common.data.MoreKeysTables
import ee.oyatl.ime.f.common.data.SoftKeyboardLayouts
import ee.oyatl.ime.f.common.data.SymbolTables
import ee.oyatl.ime.f.common.switcher.InAppIMESwitcher
import ee.oyatl.ime.f.common.table.MoreKeysTable
import ee.oyatl.ime.f.common.view.DefaultInputViewManager
import ee.oyatl.ime.f.common.view.InputViewManager
import ee.oyatl.ime.f.core.table.CharOverrideTable
import ee.oyatl.ime.f.core.table.SimpleCodeConvertTable
import ee.oyatl.ime.f.en.data.LayoutPresets

class FboardIMEEnglish: DefaultTableIME() {

    override fun onCreate() {
        super.onCreate()
        val layoutName = pref.getString("keyboard_layout", null) ?: "qwerty"
        val layout = LayoutPresets.SOFT_LAYOUT_PRESET_MOBILE[layoutName] ?: SoftKeyboardLayouts.LAYOUT_QWERTY_MOBILE
        inAppIMESwitcher.list += "base" to InAppIMESwitcher.State.Default(
            inputViewManager = DefaultInputViewManager(this,
                InputViewManager.generateInputViewParams(pref, layout)),
            moreKeysTable = MoreKeysTable(),
            convertTable = SimpleCodeConvertTable(),
            overrideTable = CharOverrideTable(),
        )
        inAppIMESwitcher.list += "symbols" to InAppIMESwitcher.State.Default(
            inputViewManager = DefaultInputViewManager(this,
                InputViewManager.generateInputViewParams(
                    pref, SoftKeyboardLayouts.LAYOUT_QWERTY_MOBILE_SEMICOLON)),
            moreKeysTable = MoreKeysTables.MORE_KEYS_TABLE_G,
            convertTable = SymbolTables.LAYOUT_SYMBOLS_G,
            overrideTable = CharOverrideTable(),
        )
        inAppIMESwitcher.transition += listOf("base", "symbols")
    }

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