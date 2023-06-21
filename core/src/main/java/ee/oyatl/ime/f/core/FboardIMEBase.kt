package ee.oyatl.ime.f.core

import android.inputmethodservice.InputMethodService
import android.util.TypedValue
import android.view.View
import ee.oyatl.ime.f.core.data.SoftKeyboardLayouts
import ee.oyatl.ime.f.core.view.FlickDirection
import ee.oyatl.ime.f.core.view.KeyboardListener
import ee.oyatl.ime.f.core.view.StackedViewKeyboardView
import ee.oyatl.ime.f.core.view.Themes

abstract class FboardIMEBase: InputMethodService() {

    private val listener = object: KeyboardListener {
        override fun onKeyClick(code: Int, output: String?) {
            sendDownUpKeyEvents(code)
        }

        override fun onKeyLongClick(code: Int, output: String?) {
        }

        override fun onKeyDown(code: Int, output: String?) {
        }

        override fun onKeyUp(code: Int, output: String?) {
        }

        override fun onKeyFlick(direction: FlickDirection, code: Int, output: String?) {
        }
    }

    override fun onCreateInputView(): View {
        val rowHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 52f, resources.displayMetrics).toInt()
        val keyboardView = StackedViewKeyboardView(this, null,
            SoftKeyboardLayouts.LAYOUT_QWERTY_MOBILE, Themes.Dynamic, listener, false, rowHeight)
        return keyboardView
    }

}