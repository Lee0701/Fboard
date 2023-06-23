package ee.oyatl.ime.f.common.settings

import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.AttributeSet
import androidx.preference.Preference
import ee.oyatl.ime.f.common.R

class EnableInputMethodPreference(
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
        context.startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS))
    }
}