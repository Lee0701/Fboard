package ee.oyatl.ime.f.common.view

import android.content.Context
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.view.View
import ee.oyatl.ime.f.common.layouts.SoftKeyboardLayouts
import ee.oyatl.ime.f.common.view.model.KeyboardLayout
import ee.oyatl.ime.f.common.view.keyboard.KeyboardListener
import ee.oyatl.ime.f.common.view.keyboard.Theme
import ee.oyatl.ime.f.common.view.keyboard.Themes

interface InputViewManager {
    val keyboardListener: KeyboardListener
    val params: Params

    fun createView(context: Context): View
    fun updateLabelsAndIcons(labels: Map<Int, CharSequence>, icons: Map<Int, Drawable>)

    data class Params(
        val keyboardLayout: KeyboardLayout,
        val keyboardTheme: Theme,
        val unifyHeight: Boolean,
        val rowHeight: Int,
    )

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