package ee.oyatl.ime.f.common.settings

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import androidx.preference.Preference
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import ee.oyatl.ime.f.common.R

class OssLicensesMenuPreference(
    context: Context,
    atts: AttributeSet?,
): Preference(context, atts) {
    init {
        layoutResource = R.layout.preference_inline
    }
    override fun onClick() {
        context.startActivity(Intent(context, OssLicensesMenuActivity::class.java))
        super.onClick()
    }
}