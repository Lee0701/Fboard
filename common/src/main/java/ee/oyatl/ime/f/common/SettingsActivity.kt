package ee.oyatl.ime.f.common

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Build
import android.os.Bundle
import androidx.annotation.XmlRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.google.android.material.elevation.SurfaceColors
import org.json.JSONArray
import org.json.JSONObject

abstract class SettingsActivity: AppCompatActivity(), OnSharedPreferenceChangeListener {
    private val pref: SharedPreferences get() = PreferenceManager.getDefaultSharedPreferences(this)

    abstract val prefResId: Int
    private var invalidated: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportFragmentManager.fragmentFactory = FboardFragmentFactory(prefResId)
        val fragment = supportFragmentManager.fragmentFactory
            .instantiate(classLoader, SettingsFragment::class.java.name)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, fragment)
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
        PreferenceManager.setDefaultValues(this, R.xml.preferences_common, false)
        pref.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onStop() {
        super.onStop()
        invalidated = true
        syncSettings(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        pref.unregisterOnSharedPreferenceChangeListener(this)
    }

    class SettingsFragment(
        @XmlRes val prefResId: Int,
    ): PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.preferences_common, rootKey)
            addPreferencesFromResource(prefResId)
        }
    }

    class FboardFragmentFactory(
        @XmlRes val prefResId: Int,
    ): FragmentFactory() {
        override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
            return when(className) {
                SettingsFragment::class.java.name -> SettingsFragment(prefResId)
                else -> super.instantiate(classLoader, className)
            }
        }
    }

    override fun onSharedPreferenceChanged(pref: SharedPreferences?, key: String?) {
        if(invalidated) {
            val fragment = supportFragmentManager.fragmentFactory
                .instantiate(classLoader, SettingsFragment::class.java.name)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, fragment)
                .commit()
        }
        invalidated = false
    }

    companion object {
        const val INTENT_ACTION_SYNC_SETTINGS = "ee.oyatl.ime.f.intent.action.SYNC_SETTINGS"
        fun syncSettings(context: Context) {
            val pref = PreferenceManager.getDefaultSharedPreferences(context)
            val packages = context.resources.getStringArray(R.array.sync_settings_packages).filterNotNull().toList()
            val keys = context.resources.getStringArray(R.array.sync_settings_keys).toList()
            val json = JSONArray().apply {
                keys.forEach { key ->
                    val value: Any = pref.all.entries.find { it.key == key }?.value ?: return@forEach
                    val type = value::class.simpleName
                    val entry = JSONObject().apply {
                        put("key", key)
                        put("type", type)
                        put("value", value)
                    }
                    put(entry)
                }
            }
            (packages - context.packageName).forEach { pkg ->
                val intent = Intent().apply {
                    action = INTENT_ACTION_SYNC_SETTINGS
                    `package` = pkg
                    type = "application/json"
                    putExtra("json", json.toString())
                }
                context.sendBroadcast(intent)
            }
        }

    }
}