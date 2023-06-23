package ee.oyatl.ime.f.common.settings

import android.os.Build
import android.os.Bundle
import androidx.annotation.XmlRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.google.android.material.elevation.SurfaceColors
import ee.oyatl.ime.f.common.R

abstract class SettingsActivity: AppCompatActivity() {

    abstract val prefResId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment(prefResId))
            .commit()
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val color = SurfaceColors.SURFACE_2.getColor(this)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor = color
            window.statusBarColor = color
            val insetsController = WindowCompat.getInsetsController(window, window.decorView)
            insetsController.isAppearanceLightStatusBars = true
            insetsController.isAppearanceLightNavigationBars = true
        }
        PreferenceManager.setDefaultValues(this, prefResId, false)
    }

    class SettingsFragment(
        @XmlRes val prefResId: Int,
    ): PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(prefResId, rootKey)
        }
    }
}