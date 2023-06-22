package ee.oyatl.ime.f.view

import android.inputmethodservice.InputMethodService
import android.view.View

abstract class DefaultIMEBase: InputMethodService(), InputViewManager, KeyboardListener {
    override val keyboardListener: KeyboardListener = this

    override fun onCreateInputView(): View? {
        return createInputView(this)
    }

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