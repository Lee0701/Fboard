package ee.oyatl.ime.f.en

import ee.oyatl.ime.f.view.data.SoftKeyboardLayouts
import ee.oyatl.ime.f.view.model.KeyboardLayout
import ee.oyatl.ime.f.view.DefaultFboardIMEBase
import ee.oyatl.ime.f.view.InputViewManager
import ee.oyatl.ime.f.view.keyboard.Theme
import ee.oyatl.ime.f.view.keyboard.Themes

class FboardIMEEnglish: DefaultFboardIMEBase(
    params = generateInputViewParams(),
), InputViewManager {

    companion object {
        fun generateInputViewParams(): InputViewManager.Params = InputViewManager.Params(
            keyboardLayout = SoftKeyboardLayouts.LAYOUT_QWERTY_MOBILE,
            keyboardTheme = Themes.Dynamic,
            unifyHeight = false,
            rowHeight = 54,
        )
    }
}