package ee.oyatl.ime.f.common

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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

abstract class SettingsActivityBase: AppCompatActivity() {
    private val pref: SharedPreferences get() = PreferenceManager.getDefaultSharedPreferences(this)

    abstract val prefResId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val synced = maybeImportSettings()
        if(synced) return finish()

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
    }

    override fun onPause() {
        super.onPause()
        syncSettings(this)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun maybeImportSettings(): Boolean {
        if(intent.action == Intent.ACTION_SEND
            && intent.type == "application/json"
            && intent.`package` == packageName) {

            importSettings()
            return true
        } else {
            return false
        }
    }

    private fun getFloatValue(value: Any): Float {
        if(value is Float) return value
        if(value is Int) return value.toFloat()
        throw IllegalArgumentException("Value $value could not be converted to Float")
    }

    private fun importSettings() {
        moveTaskToBack(true)
        val editor = pref.edit()
        val json = JSONArray(intent.getStringExtra("json") ?: "[]")
        (0 until json.length()).forEach { idx ->
            val entry = json.getJSONObject(idx)
            val key = entry.getString("key")
            val type = entry.getString("type")
            val value = entry.get("value")
            @Suppress("UNCHECKED_CAST")
            when(type) {
                Boolean::class.simpleName -> editor.putBoolean(key, value as Boolean)
                Int::class.simpleName -> editor.putInt(key, value as Int)
                Float::class.simpleName -> editor.putFloat(key, getFloatValue(value))
                String::class.simpleName -> editor.putString(key, value as String)
                Set::class.simpleName -> editor.putStringSet(key, value as MutableSet<String>?)
            }
        }
        editor.apply()
    }

    class SettingsFragment(
        @XmlRes val prefResId: Int,
    ): PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(prefResId, rootKey)
        }
    }

    companion object {
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
                    action = Intent.ACTION_SEND
                    `package` = pkg
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    type = "application/json"
                    putExtra("json", json.toString())
                }
                context.startActivity(intent)
            }
        }

    }

}