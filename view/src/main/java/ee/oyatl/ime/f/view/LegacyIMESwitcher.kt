package ee.oyatl.ime.f.view

import android.content.ContentResolver
import android.content.Context
import android.inputmethodservice.InputMethodService
import android.provider.Settings
import android.view.inputmethod.InputMethodManager
import ee.oyatl.ime.f.core.FboardIMEBase
import ee.oyatl.ime.f.core.IMESwitcher
import kotlin.math.min

@Suppress("DEPRECATION")
class LegacyIMESwitcher(service: InputMethodService): IMESwitcher {

    private val imm = service.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    private val contentResolver: ContentResolver = service.contentResolver

    override fun previous(): Boolean {
        return imm.switchToLastInputMethod(null)
    }

    override fun next(onlyFboard: Boolean, onlyCurrentIme: Boolean): Boolean {
        if(onlyFboard) {
            val list = this.list()
            val index = list.indexOf(current()) + 1
            val id = list[index % list.size]
            imm.setInputMethod(null, id)
            return true
        } else {
            return imm.switchToNextInputMethod(null, onlyCurrentIme)
        }
    }

    override fun current(): String {
        return Settings.Secure.getString(contentResolver, Settings.Secure.DEFAULT_INPUT_METHOD)
    }

    override fun list(): List<String> {
        return imm.enabledInputMethodList
            .filter { it.packageName.startsWith(FboardIMEBase.FBOARD_PACKAGE_NAME_PREFIX) }
            .map { it.id }
    }
}