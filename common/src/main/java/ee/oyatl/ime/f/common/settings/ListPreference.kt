package ee.oyatl.ime.f.common.settings

import android.content.Context
import android.util.AttributeSet
import androidx.preference.ListPreference
import ee.oyatl.ime.f.common.R

class ListPreference(
    context: Context,
    attrs: AttributeSet?,
): ListPreference(context, attrs) {
    init {
        layoutResource = R.layout.preference_inline
    }
}