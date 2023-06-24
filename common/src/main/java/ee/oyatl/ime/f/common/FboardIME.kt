package ee.oyatl.ime.f.common

interface FboardIME {

    fun onCreate() = Unit
    fun onDestroy() = Unit
    fun onUpdate() = Unit
    fun onReset() = Unit
    fun onPrintingKey(keyCode: Int): Boolean = false
    fun onDeleteKey(): Boolean = false
    fun onSpace(): Boolean = false
    fun onActionKey(): Boolean = false
    fun onLanguageKey(): Boolean = false
    fun onSymbolsKey(): Boolean = false

    companion object {
        const val FBOARD_PACKAGE_NAME_PREFIX = "ee.oyatl.ime.f"
    }
}