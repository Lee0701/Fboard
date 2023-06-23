package ee.oyatl.ime.f.common

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Build
import android.os.Bundle
import androidx.annotation.XmlRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.google.android.material.elevation.SurfaceColors

abstract class SettingsActivity: AppCompatActivity(), OnSharedPreferenceChangeListener {

    private val pref: SharedPreferences get() = getSharedPreferences(SHARED_PREF_DEFAULT, 0)
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
        PreferenceManager.setDefaultValues(this, SHARED_PREF_DEFAULT, 0, prefResId, false)
        pref.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        pref.unregisterOnSharedPreferenceChangeListener(this)
    }

    class SettingsFragment(
        @XmlRes val prefResId: Int,
    ): PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            preferenceManager.sharedPreferencesName = SHARED_PREF_DEFAULT
            setPreferencesFromResource(prefResId, rootKey)
        }
    }

    override fun onSharedPreferenceChanged(pref: SharedPreferences?, key: String?) {
        val packages = setOf<String>(
            "ee.oyatl.ime.f.en",
            "ee.oyatl.ime.f.ko",
        ) - packageName
        val keys = setOf<String>(
            "appearance_keyboard_height",
            "appearance_unify_height",
        )
        if(pref == null || key == null || key !in keys) return
//        pref.getBoolean()
//        pref.getFloat()
//        pref.getString()
//        pref.getStringSet()
//        pref.getInt()
//        pref.getLong()
        val editors = packages.map { pkg ->
            val pkgContext = createPackageContext(pkg, 0)
            val pkgPref = pkgContext.getSharedPreferences(SHARED_PREF_DEFAULT, Context.MODE_PRIVATE)
            pkgPref.edit()
        }
        pref.all.entries.forEach { (k, v) ->
            if(k == key) when(v) {
                is Boolean -> editors.forEach { it.putBoolean(k, v) }
                is Int -> editors.forEach { it.putInt(k, v) }
                is Long -> editors.forEach { it.putLong(k, v) }
                is Float -> editors.forEach { it.putFloat(k, v) }
                is String -> editors.forEach { it.putString(k, v) }
                is Set<*> -> editors.forEach { it.putStringSet(k, v as Set<String>?) }
                else -> Unit
            }
        }
        editors.forEach { it.apply() }
    }

    companion object {
        const val SHARED_PREF_DEFAULT = "default"
    }

}