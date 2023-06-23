package ee.oyatl.ime.f.common.settings

import android.content.Context
import android.util.AttributeSet
import android.view.inputmethod.InputMethodManager
import androidx.preference.Preference
import ee.oyatl.ime.f.common.R

class ChooseInputMethodPreference(
    context: Context,
    attrs: AttributeSet?,
): Preference(context, attrs) {
    init {
        val appName = context.getString(R.string.app_name)
        this.title = title.toString().format(appName)
        this.summary = summary.toString().format(appName)
        layoutResource = R.layout.preference_inline
    }

    override fun onClick() {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showInputMethodPicker()
    }
}