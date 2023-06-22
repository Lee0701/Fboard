package ee.oyatl.ime.f.view

import android.view.View
import ee.oyatl.ime.f.core.FboardIMEBase
import ee.oyatl.ime.f.view.keyboard.FlickDirection
import ee.oyatl.ime.f.view.keyboard.InputViewManager
import ee.oyatl.ime.f.view.keyboard.KeyboardListener

abstract class DefaultFboardIMEBase: FboardIMEBase(), InputViewManager, KeyboardListener {
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