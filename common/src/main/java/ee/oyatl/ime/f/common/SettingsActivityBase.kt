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
import org.json.JSONObject

abstract class SettingsActivityBase: AppCompatActivity() {
    private val pref: SharedPreferences get() = PreferenceManager.getDefaultSharedPreferences(this)

    abstract val prefResId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sync = maybeSyncSettings()
        if(sync) return finish()

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

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun maybeSyncSettings(): Boolean {
        if(intent.action == Intent.ACTION_SEND && intent.type == "application/json") {
            val editor = pref.edit()
            val json = JSONObject(intent.getStringExtra("json") ?: "{}")
            json.keys().forEach { key ->
                val value = json.get(key)
                @Suppress("UNCHECKED_CAST")
                when(value) {
                    is Boolean -> editor.putBoolean(key, value)
                    is Int -> editor.putInt(key, value)
                    is Float -> editor.putFloat(key, value)
                    is String -> editor.putString(key, value)
                    is Set<*> -> editor.putStringSet(key, value as MutableSet<String>?)
                }
            }
            editor.apply()
            return true
        } else {
            return false
        }
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
            val packages: Set<String> = setOf(
                "ee.oyatl.ime.f.en",
                "ee.oyatl.ime.f.ko",
            ) - context.packageName
            val keys: Set<String> = setOf(
                "appearance_theme",
                "appearance_keyboard_height",
                "appearance_keyboard_height",
                "appearance_unify_height",
                "appearance_haptic_feedback",
                "appearance_sound_feedback",
            )
            val json = JSONObject().apply {
                keys.forEach { key ->
                    val value: Any = pref.all.entries.find { it.key == key }?.value ?: return
                }
            }
            packages.forEach { pkg ->
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    `package` = pkg
                    type = "application/json"
                    putExtra("json", json.toString())
                }
                context.startActivity(intent)
            }
        }

    }

}