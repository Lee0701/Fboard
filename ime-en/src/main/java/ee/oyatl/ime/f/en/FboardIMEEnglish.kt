package ee.oyatl.ime.f.en

import android.graphics.drawable.Drawable
import android.view.KeyEvent
import ee.oyatl.ime.f.common.DefaultFboardIMEBase
import ee.oyatl.ime.f.common.layouts.SoftKeyboardLayouts
import ee.oyatl.ime.f.common.view.InputViewManager
import ee.oyatl.ime.f.common.view.keyboard.Themes

class FboardIMEEnglish: DefaultFboardIMEBase(
    params = generateInputViewParams(),
) {

    override fun onUpdate() {
        val state = modifierState
        val labels = (KeyEvent.KEYCODE_A .. KeyEvent.KEYCODE_Z)
            .map { code -> code to keyCharacterMap.get(code, state.asMetaState()).toChar().toString() }
            .toMap()
        val icons = mapOf<Int, Drawable>()
        inputViewManager.updateLabelsAndIcons(
            labels = labels,
            icons = icons,
        )
    }

    companion object {
        fun generateInputViewParams(): InputViewManager.Params = InputViewManager.Params(
            keyboardLayout = SoftKeyboardLayouts.LAYOUT_QWERTY_MOBILE,
            keyboardTheme = Themes.Dynamic,
            unifyHeight = false,
            rowHeight = 54,
        )
    }
}