package ee.oyatl.ime.f.common

import com.google.android.material.color.DynamicColors

abstract class DynamicColorsAppBase: FboardAppBase() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }

}