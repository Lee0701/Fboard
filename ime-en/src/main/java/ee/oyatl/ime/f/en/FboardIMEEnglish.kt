package ee.oyatl.ime.f.en

import ee.oyatl.ime.f.common.view.DefaultFboardIMEBase
import ee.oyatl.ime.f.common.view.data.SoftKeyboardLayouts
import ee.oyatl.ime.f.common.view.InputViewManager
import ee.oyatl.ime.f.common.view.keyboard.Themes

class FboardIMEEnglish: DefaultFboardIMEBase(
    params = generateInputViewParams(),
) {

    override fun init() {
    }

    override fun destroy() {
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