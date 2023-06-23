package ee.oyatl.ime.f.common.settings

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import androidx.preference.Preference
import androidx.preference.PreferenceManager
import ee.oyatl.ime.f.common.R
import ee.oyatl.ime.f.common.SettingsActivityBase
import org.json.JSONObject

class SyncSettingsPreference(
    context: Context,
    atts: AttributeSet?,
): Preference(context, atts) {

    init {
        layoutResource = R.layout.preference_inline
    }

    override fun onClick() {
        super.onClick()
        SettingsActivityBase.syncSettings(context)
    }

}