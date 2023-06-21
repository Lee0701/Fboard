package ee.oyatl.ime.f.core

import android.app.Application
import com.google.android.material.color.DynamicColors

abstract class FboardAppBase: Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}