package ee.oyatl.ime.f.en

import android.graphics.drawable.Drawable
import android.view.KeyEvent
import ee.oyatl.ime.f.common.DefaultFboardIMEBase
import ee.oyatl.ime.f.common.layouts.SoftKeyboardLayouts
import ee.oyatl.ime.f.common.view.model.KeyboardLayout
import ee.oyatl.ime.f.core.table.CharOverrideTable
import ee.oyatl.ime.f.core.table.CodeConvertTable
import ee.oyatl.ime.f.core.table.SimpleCodeConvertTable

class FboardIMEEnglish: DefaultFboardIMEBase() {

    override val keyboardLayout: KeyboardLayout = SoftKeyboardLayouts.LAYOUT_QWERTY_MOBILE
    override val overrideTable: CharOverrideTable = CharOverrideTable()
    override val convertTable: CodeConvertTable = SimpleCodeConvertTable()

    override fun onUpdate() {
        val state = modifierState
        val labelsRange = KeyEvent.KEYCODE_UNKNOWN .. KeyEvent.KEYCODE_SEARCH
        val labels = labelsRange.associateWith { code ->
            keyCharacterMap.get(code, state.asMetaState()).toChar().toString()
        }
        val icons = mapOf<Int, Drawable>()
        updateLabelsAndIcons(
            labels = labels,
            icons = icons,
        )
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