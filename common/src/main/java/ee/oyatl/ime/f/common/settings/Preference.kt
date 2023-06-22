package ee.oyatl.ime.f.common.settings

import android.content.Context
import android.util.AttributeSet
import androidx.preference.Preference
import ee.oyatl.ime.f.common.R

class Preference(
    context: Context,
    atts: AttributeSet?,
): Preference(context, atts) {
    init {
        layoutResource = R.layout.preference_inline
    }
}