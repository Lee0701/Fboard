package ee.oyatl.ime.f.common

import android.inputmethodservice.InputMethodService
import android.view.KeyEvent
import ee.oyatl.ime.f.common.view.keyboard.FlickDirection
import ee.oyatl.ime.f.common.view.keyboard.KeyboardListener

class DirectIME: InputMethodService(), FboardIME, KeyboardListener {

    override fun onKeyDown(code: Int, output: String?) {
        val event = KeyEvent(KeyEvent.ACTION_DOWN, code)
        onKeyDown(code, event)
    }

    override fun onKeyUp(code: Int, output: String?) {
        val event = KeyEvent(KeyEvent.ACTION_UP, code)
        onKeyDown(code, event)
    }

    override fun onKeyClick(code: Int, output: String?) = Unit

    override fun onKeyLongClick(code: Int, output: String?) = Unit

    override fun onKeyFlick(direction: FlickDirection, code: Int, output: String?) = Unit

    override fun onCreate() {
        super<InputMethodService>.onCreate()
    }

    override fun onDestroy() {
        super<InputMethodService>.onDestroy()
    }

}