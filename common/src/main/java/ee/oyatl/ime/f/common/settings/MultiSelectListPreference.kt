package ee.oyatl.ime.f.common.settings

import android.content.Context
import android.util.AttributeSet
import androidx.preference.MultiSelectListPreference
import ee.oyatl.ime.f.common.R

class MultiSelectListPreference(
    context: Context,
    attrs: AttributeSet?,
): MultiSelectListPreference(context, attrs) {
    init {
        layoutResource = R.layout.preference_inline
    }
}