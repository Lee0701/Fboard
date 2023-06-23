package ee.oyatl.ime.f.en

import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.view.KeyEvent
import androidx.preference.PreferenceManager
import ee.oyatl.ime.f.common.DefaultFboardIMEBase
import ee.oyatl.ime.f.common.input.CharOverrideTable
import ee.oyatl.ime.f.common.input.CodeConvertTable
import ee.oyatl.ime.f.common.input.SimpleCodeConvertTable
import ee.oyatl.ime.f.common.layouts.SoftKeyboardLayouts
import ee.oyatl.ime.f.common.view.InputViewManager
import ee.oyatl.ime.f.common.view.keyboard.Themes

class FboardIMEEnglish: DefaultFboardIMEBase() {
    val pref: SharedPreferences get() = PreferenceManager.getDefaultSharedPreferences(this)
    override val params: InputViewManager.Params get() = generateInputViewParams(pref)

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

    companion object {
        fun generateInputViewParams(pref: SharedPreferences): InputViewManager.Params {
            val themeId = pref.getString("appearance_theme", "theme_dynamic")
            val theme = Themes.of(themeId)
            val unifyHeight = pref.getBoolean("appearance_unify_height", false)
            val rowHeight = pref.getFloat("appearance_keyboard_height", 55f)
            return InputViewManager.Params(
                keyboardLayout = SoftKeyboardLayouts.LAYOUT_QWERTY_MOBILE,
                keyboardTheme = theme,
                unifyHeight = unifyHeight,
                rowHeight = rowHeight.toInt(),
            )
        }
    }
}