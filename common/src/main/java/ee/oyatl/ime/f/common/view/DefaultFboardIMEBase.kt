package ee.oyatl.ime.f.common.view

import android.content.Context
import android.graphics.drawable.Drawable
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

    open fun onUpdate() = Unit
    open fun onReset() = Unit
    open fun onPrintingKey(keyCode: Int): Boolean = false
    open fun onDeleteKey(): Boolean = false
    open fun onSpace(): Boolean = false
    open fun onActionKey(): Boolean = false
    private fun onLanguageKey(): Boolean {
        return imeSwitcher.next()
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onCreateInputView(): View {
        val view = this.createView(this)
        this.onUpdate()
        return view
    }

    override fun createView(context: Context): View {
        return inputViewManager.createView(context)
    }

    override fun updateLabelsAndIcons(labels: Map<Int, CharSequence>, icons: Map<Int, Drawable>) {
        inputViewManager.updateLabelsAndIcons(labels, icons)
    }

    override fun onKeyClick(code: Int, output: String?) {
        val consumed: Boolean = if(isPrintingKey(code)) {
            onPrintingKey(code)
        } else when(code) {
            KeyEvent.KEYCODE_DEL -> onDeleteKey()
            KeyEvent.KEYCODE_SPACE -> onSpace()
            KeyEvent.KEYCODE_ENTER -> onActionKey()
            KeyEvent.KEYCODE_LANGUAGE_SWITCH -> onLanguageKey()
            else -> false
        }
        if(!consumed) sendDownUpKeyEvents(code)
        this.onUpdate()
    }

    override fun onKeyLongClick(code: Int, output: String?) {
    }

    override fun onKeyDown(code: Int, output: String?) {
    }

    override fun onKeyUp(code: Int, output: String?) {
    }

    override fun onKeyFlick(direction: FlickDirection, code: Int, output: String?) {
    }

    private fun isPrintingKey(code: Int): Boolean = KeyEvent(KeyEvent.ACTION_DOWN, code).isPrintingKey

    override fun current(): String {
        return Settings.Secure.getString(contentResolver, Settings.Secure.DEFAULT_INPUT_METHOD)
    }

}