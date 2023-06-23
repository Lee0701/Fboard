package ee.oyatl.ime.f.common

import android.content.Context.INPUT_METHOD_SERVICE
import android.inputmethodservice.InputMethodService
import android.os.Build
import android.provider.Settings
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import ee.oyatl.ime.f.common.FboardIMEBase.Companion.FBOARD_PACKAGE_NAME_PREFIX

class NewIMESwitcher(
    private val ime: InputMethodService,
): IMESwitcher {

    @RequiresApi(Build.VERSION_CODES.P)
    override fun previous(): Boolean {
        return ime.switchToPreviousInputMethod()
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun next(onlyFboard: Boolean, onlyCurrentIme: Boolean): Boolean {
        if(onlyFboard) {
            val list = this.list().filter { !it.contains(".sym") }
            val index = list.indexOf(current()) + 1
            val id = list[index % list.size]
            ime.switchInputMethod(id)
            return true
        } else {
            return ime.switchToNextInputMethod(onlyCurrentIme)
        }
    }

    override fun symbols(): Boolean {
        val symbols = this.list().find { it.contains(".sym") }
        if(symbols != null) ime.switchInputMethod(symbols)
        return true
    }

    override fun list(): List<String> {
        val imm = ime.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        return imm.enabledInputMethodList
            .filter { it.packageName.startsWith(FBOARD_PACKAGE_NAME_PREFIX) }
            .map { it.id }
    }

    override fun current(): String {
        return Settings.Secure.getString(ime.contentResolver, Settings.Secure.DEFAULT_INPUT_METHOD)
    }

}