package ee.oyatl.ime.f.ko

import ee.oyatl.ime.f.core.data.SoftKeyboardLayouts
import ee.oyatl.ime.f.view.DefaultFboardIMEBase
import ee.oyatl.ime.f.view.InputViewManager
import ee.oyatl.ime.f.view.keyboard.Themes

class FboardIMEKorean: DefaultFboardIMEBase(
    params = generateInputViewParams(),
) {

    companion object {
        fun generateInputViewParams(): InputViewManager.Params = InputViewManager.Params(
            keyboardLayout = SoftKeyboardLayouts.LAYOUT_QWERTY_MOBILE,
            keyboardTheme = Themes.Dynamic,
            unifyHeight = false,
            rowHeight = 54,
        )
    }
}