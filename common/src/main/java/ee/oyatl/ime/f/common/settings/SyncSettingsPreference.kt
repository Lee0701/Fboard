package ee.oyatl.ime.f.common.settings

import android.content.Context
import android.util.AttributeSet
import androidx.preference.Preference
import ee.oyatl.ime.f.common.R
import ee.oyatl.ime.f.common.SettingsActivity

class SyncSettingsPreference(
    context: Context,
    atts: AttributeSet?,
): Preference(context, atts) {

    init {
        layoutResource = R.layout.preference_inline
    }

    override fun onClick() {
        super.onClick()
        SettingsActivity.syncSettings(context)
    }

}