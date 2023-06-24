package ee.oyatl.ime.f.common.switcher

import android.content.Context.INPUT_METHOD_SERVICE
import android.inputmethodservice.InputMethodService
import android.os.Build
import android.provider.Settings
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import ee.oyatl.ime.f.common.FboardIME.Companion.FBOARD_PACKAGE_NAME_PREFIX

class NewIMESwitcher(
    private val ime: InputMethodService,
): IMESwitcher {

    private val imm = ime.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

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

    override fun list(): List<String> {
        return imm.enabledInputMethodList
            .filter { it.packageName.startsWith(FBOARD_PACKAGE_NAME_PREFIX) }
            .map { it.id }
    }

    override fun current(): String {
        return Settings.Secure.getString(ime.contentResolver, Settings.Secure.DEFAULT_INPUT_METHOD)
    }

}