package ee.oyatl.ime.f.common

import android.inputmethodservice.InputMethodService
import android.os.Build
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi

abstract class FboardIMEBase: InputMethodService(), ee.oyatl.ime.f.common.IMESwitcher {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun previous(): Boolean {
        return this.switchToPreviousInputMethod()
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun next(onlyFboard: Boolean, onlyCurrentIme: Boolean): Boolean {
        if(onlyFboard) {
            val list = this.list()
            val index = list.indexOf(current()) + 1
            val id = list[index % list.size]
            switchInputMethod(id)
            return true
        } else {
            return switchToNextInputMethod(onlyCurrentIme)
        }
    }

    override fun list(): List<String> {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        return imm.enabledInputMethodList
            .filter { it.packageName.startsWith(FBOARD_PACKAGE_NAME_PREFIX) }
            .map { it.id }
    }

    companion object {
        const val FBOARD_PACKAGE_NAME_PREFIX = "ee.oyatl.ime.f"
    }
}