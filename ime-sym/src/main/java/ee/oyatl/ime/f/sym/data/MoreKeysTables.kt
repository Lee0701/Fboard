package ee.oyatl.ime.f.sym.data

import android.view.KeyEvent
import ee.oyatl.ime.f.common.table.MoreKeysTable
import ee.oyatl.ime.f.common.view.model.KeyboardLayout
import ee.oyatl.ime.f.common.view.model.Row

object MoreKeysTables {

    val MORE_KEYS_Q = KeyboardLayout(
        Row.ofOutputs("⅙⅐⅛⅑⅒"),
        Row.ofOutputs("¹½⅓¼⅕"),
    )

    val MORE_KEYS_G = MoreKeysTable(
        KeyEvent.KEYCODE_Q to MORE_KEYS_Q,
    )

}