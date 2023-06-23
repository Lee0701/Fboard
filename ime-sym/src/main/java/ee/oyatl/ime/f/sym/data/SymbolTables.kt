package ee.oyatl.ime.f.sym.data

import android.view.KeyEvent
import ee.oyatl.ime.f.core.table.SimpleCodeConvertTable
import ee.oyatl.ime.f.core.table.SimpleCodeConvertTable.Entry

object SymbolTables {

    val LAYOUT_SYMBOLS_G = SimpleCodeConvertTable(
        KeyEvent.KEYCODE_Q to Entry('1'.code, '~'.code),
        KeyEvent.KEYCODE_W to Entry('2'.code, '`'.code),
        KeyEvent.KEYCODE_E to Entry('3'.code, '|'.code),
        KeyEvent.KEYCODE_R to Entry('4'.code, '•'.code),
        KeyEvent.KEYCODE_T to Entry('5'.code, '√'.code),
        KeyEvent.KEYCODE_Y to Entry('6'.code, 'π'.code),
        KeyEvent.KEYCODE_U to Entry('7'.code, '÷'.code),
        KeyEvent.KEYCODE_I to Entry('8'.code, '×'.code),
        KeyEvent.KEYCODE_O to Entry('9'.code, '¶'.code),
        KeyEvent.KEYCODE_P to Entry('0'.code, '∆'.code),

        KeyEvent.KEYCODE_A to Entry('@'.code, '€'.code),
        KeyEvent.KEYCODE_S to Entry('#'.code, '¥'.code),
        KeyEvent.KEYCODE_D to Entry('£'.code, '$'.code),
        KeyEvent.KEYCODE_F to Entry('_'.code, '¢'.code),
        KeyEvent.KEYCODE_G to Entry('&'.code, '^'.code),
        KeyEvent.KEYCODE_H to Entry('-'.code, '°'.code),
        KeyEvent.KEYCODE_J to Entry('+'.code, '='.code),
        KeyEvent.KEYCODE_K to Entry('('.code, '{'.code),
        KeyEvent.KEYCODE_L to Entry(')'.code, '}'.code),
        KeyEvent.KEYCODE_SEMICOLON to Entry('/'.code, '\\'.code),

        KeyEvent.KEYCODE_Z to Entry('*'.code, '%'.code),
        KeyEvent.KEYCODE_X to Entry('"'.code, '©'.code),
        KeyEvent.KEYCODE_C to Entry('\''.code, '®'.code),
        KeyEvent.KEYCODE_V to Entry(':'.code, '™'.code),
        KeyEvent.KEYCODE_B to Entry(';'.code, '✓'.code),
        KeyEvent.KEYCODE_N to Entry('!'.code, '['.code),
        KeyEvent.KEYCODE_M to Entry('?'.code, ']'.code),
    )

}