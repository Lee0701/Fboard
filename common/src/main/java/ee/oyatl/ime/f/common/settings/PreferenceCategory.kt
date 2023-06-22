package ee.oyatl.ime.f.common.settings

import android.content.Context
import android.util.AttributeSet
import androidx.preference.PreferenceCategory
import ee.oyatl.ime.f.common.R

class PreferenceCategory(
    context: Context,
    attrs: AttributeSet?,
): PreferenceCategory(context, attrs) {
    init {
        layoutResource = R.layout.preference_category
    }
}