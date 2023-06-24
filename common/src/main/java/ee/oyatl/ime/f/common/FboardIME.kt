package ee.oyatl.ime.f.common

import android.inputmethodservice.InputMethodService

abstract class FboardIME: InputMethodService() {
    
    companion object {
        const val FBOARD_PACKAGE_NAME_PREFIX = "ee.oyatl.ime.f"
    }
}