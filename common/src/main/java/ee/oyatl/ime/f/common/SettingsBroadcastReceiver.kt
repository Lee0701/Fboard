package ee.oyatl.ime.f.common

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import org.json.JSONArray

class SettingsBroadcastReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if(context == null || intent == null) return
        val pref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
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

    private fun getFloatValue(value: Any): Float {
        if(value is Float) return value
        if(value is Int) return value.toFloat()
        throw IllegalArgumentException("Value $value could not be converted to Float")
    }

}