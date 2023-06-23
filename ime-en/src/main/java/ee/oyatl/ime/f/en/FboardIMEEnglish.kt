package ee.oyatl.ime.f.en

import android.graphics.drawable.Drawable
import android.view.KeyEvent
import ee.oyatl.ime.f.common.DefaultFboardIMEBase
import ee.oyatl.ime.f.core.table.CharOverrideTable
import ee.oyatl.ime.f.core.table.CodeConvertTable
import ee.oyatl.ime.f.core.table.SimpleCodeConvertTable

class FboardIMEEnglish: DefaultFboardIMEBase() {

    override val overrideTable: CharOverrideTable = CharOverrideTable()
    override val convertTable: CodeConvertTable = SimpleCodeConvertTable()

    override fun onUpdate() {
        val state = modifierState
        val labels = (KeyEvent.KEYCODE_A .. KeyEvent.KEYCODE_Z)
            .map { code -> code to keyCharacterMap.get(code, state.asMetaState()).toChar().toString() }
            .toMap()
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