package ee.oyatl.ime.f.view

import android.content.Context
import android.view.View
import ee.oyatl.ime.f.core.FboardIMEBase
import ee.oyatl.ime.f.view.keyboard.FlickDirection
import ee.oyatl.ime.f.view.keyboard.KeyboardListener
import ee.oyatl.ime.f.view.keyboard.StackedViewKeyboardView

abstract class DefaultFboardIMEBase(
    final override val params: InputViewManager.Params,
): FboardIMEBase(), InputViewManager, KeyboardListener {
    final override val keyboardListener: KeyboardListener = this
    open val inputViewManager: InputViewManager = DefaultInputViewManager(keyboardListener, params)

    override fun onCreateInputView(): View {
        return this.createInputView(this)
    }

    override fun createInputView(context: Context): View {
        return inputViewManager.createInputView(context)
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