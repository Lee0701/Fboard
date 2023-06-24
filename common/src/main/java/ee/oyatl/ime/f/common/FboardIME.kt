package ee.oyatl.ime.f.common

import android.inputmethodservice.InputMethodService

abstract class FboardIME: InputMethodService() {
    open fun onUpdate() = Unit
    open fun onReset() = Unit
    open fun onPrintingKey(keyCode: Int): Boolean = false
    open fun onDeleteKey(): Boolean = false
    open fun onSpace(): Boolean = false
    open fun onActionKey(): Boolean = false
    open fun onLanguageKey(): Boolean = false
    open fun onSymbolsKey(): Boolean = false

    companion object {
        const val FBOARD_PACKAGE_NAME_PREFIX = "ee.oyatl.ime.f"
    }
}