package ee.oyatl.ime.f.common

import com.google.android.material.color.DynamicColors

abstract class DynamicColorsApp: FboardApp() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }

}