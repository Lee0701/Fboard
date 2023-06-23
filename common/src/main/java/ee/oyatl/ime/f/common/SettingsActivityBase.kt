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
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.google.android.material.elevation.SurfaceColors
import org.json.JSONArray
import org.json.JSONObject

abstract class SettingsActivityBase: AppCompatActivity(), OnSharedPreferenceChangeListener {
    private val pref: SharedPreferences get() = PreferenceManager.getDefaultSharedPreferences(this)

    abstract val prefResId: Int
    private var invalidate: Boolean = false

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
        PreferenceManager.setDefaultValues(this, R.xml.root_preferences, false)
        pref.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onStop() {
        super.onStop()
        invalidate = true
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
            setPreferencesFromResource(prefResId, rootKey)
        }
    }

    override fun onSharedPreferenceChanged(pref: SharedPreferences?, key: String?) {
        if(invalidate) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment(prefResId))
                .commitAllowingStateLoss()
        }
        invalidate = false
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