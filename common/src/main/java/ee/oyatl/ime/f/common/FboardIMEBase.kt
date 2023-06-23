package ee.oyatl.ime.f.common

import android.inputmethodservice.InputMethodService
import android.os.Build
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi

abstract class FboardIMEBase: InputMethodService() {
    
    companion object {
        const val FBOARD_PACKAGE_NAME_PREFIX = "ee.oyatl.ime.f"
    }
}