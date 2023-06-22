package ee.oyatl.ime.f.common.view

import android.content.Context
import android.os.Build
import android.provider.Settings
import android.view.KeyEvent
import android.view.View
import ee.oyatl.ime.f.common.FboardIMEBase
import ee.oyatl.ime.f.common.IMESwitcher
import ee.oyatl.ime.f.common.view.keyboard.FlickDirection
import ee.oyatl.ime.f.common.view.keyboard.KeyboardListener

abstract class DefaultFboardIMEBase(
    final override val params: InputViewManager.Params,
): FboardIMEBase(), InputViewManager, KeyboardListener, IMESwitcher {

    final override val keyboardListener: KeyboardListener = this
    open val inputViewManager: InputViewManager =
        DefaultInputViewManager(
            keyboardListener,
            params
        )

    protected val imeSwitcher: IMESwitcher = when(Build.VERSION.SDK_INT) {
        in 0 until Build.VERSION_CODES.P -> LegacyIMESwitcher(
            this
        )
        else -> this
    }

    abstract fun init()
    abstract fun destroy()

    override fun onCreate() {
        super.onCreate()
        this.init()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.destroy()
    }

    override fun onCreateInputView(): View {
        return this.createInputView(this)
    }

    override fun createInputView(context: Context): View {
        return inputViewManager.createInputView(context)
    }

    override fun onKeyClick(code: Int, output: String?) {
        when(code) {
            KeyEvent.KEYCODE_LANGUAGE_SWITCH -> onLanguageKey()
            else -> sendDownUpKeyEvents(code)
        }
    }

    override fun onKeyLongClick(code: Int, output: String?) {
    }

    override fun onKeyDown(code: Int, output: String?) {
    }

    override fun onKeyUp(code: Int, output: String?) {
    }

    override fun onKeyFlick(direction: FlickDirection, code: Int, output: String?) {
    }

    override fun current(): String {
        return Settings.Secure.getString(contentResolver, Settings.Secure.DEFAULT_INPUT_METHOD)
    }

    private fun onLanguageKey() {
        imeSwitcher.next()
    }

}