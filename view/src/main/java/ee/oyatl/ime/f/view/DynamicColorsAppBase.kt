package ee.oyatl.ime.f.view

import com.google.android.material.color.DynamicColors
import ee.oyatl.ime.f.core.FboardAppBase

abstract class DynamicColorsAppBase: FboardAppBase() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }

}