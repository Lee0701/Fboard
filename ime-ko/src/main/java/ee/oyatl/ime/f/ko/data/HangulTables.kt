package ee.oyatl.ime.f.ko.data

import android.view.KeyEvent
import ee.oyatl.ime.f.core.table.CodeConvertTable
import ee.oyatl.ime.f.core.input.CustomKeycode
import ee.oyatl.ime.f.core.table.SimpleCodeConvertTable
import ee.oyatl.ime.f.core.table.SimpleCodeConvertTable.Entry
import ee.oyatl.ime.f.ko.hangul.JamoCombinationTable

object HangulTables {

    val LAYOUT_CUSTOM_KEYS: CodeConvertTable = SimpleCodeConvertTable(mapOf(
        CustomKeycode.KEYCODE_COMMA_PERIOD.code to Entry(0x002c, 0x002e),
    ))

    val LAYOUT_2SET_KS: CodeConvertTable = SimpleCodeConvertTable(mapOf(
        KeyEvent.KEYCODE_Q to Entry(0x3142, 0x3143),
        KeyEvent.KEYCODE_W to Entry(0x3148, 0x3149),
        KeyEvent.KEYCODE_E to Entry(0x3137, 0x3138),
        KeyEvent.KEYCODE_R to Entry(0x3131, 0x3132),
        KeyEvent.KEYCODE_T to Entry(0x3145, 0x3146),
        KeyEvent.KEYCODE_Y to Entry(0x315b),
        KeyEvent.KEYCODE_U to Entry(0x3155),
        KeyEvent.KEYCODE_I to Entry(0x3151),
        KeyEvent.KEYCODE_O to Entry(0x3150, 0x3152),
        KeyEvent.KEYCODE_P to Entry(0x3154, 0x3156),

        KeyEvent.KEYCODE_A to Entry(0x3141),
        KeyEvent.KEYCODE_S to Entry(0x3134),
        KeyEvent.KEYCODE_D to Entry(0x3147),
        KeyEvent.KEYCODE_F to Entry(0x3139),
        KeyEvent.KEYCODE_G to Entry(0x314e),
        KeyEvent.KEYCODE_H to Entry(0x3157),
        KeyEvent.KEYCODE_J to Entry(0x3153),
        KeyEvent.KEYCODE_K to Entry(0x314f),
        KeyEvent.KEYCODE_L to Entry(0x3163),

        KeyEvent.KEYCODE_Z to Entry(0x314b),
        KeyEvent.KEYCODE_X to Entry(0x314c),
        KeyEvent.KEYCODE_C to Entry(0x314a),
        KeyEvent.KEYCODE_V to Entry(0x314d),
        KeyEvent.KEYCODE_B to Entry(0x3160),
        KeyEvent.KEYCODE_N to Entry(0x315c),
        KeyEvent.KEYCODE_M to Entry(0x3161),
    )) + LAYOUT_CUSTOM_KEYS

    val COMB_2SET_STANDARD: JamoCombinationTable = JamoCombinationTable(
        listOf(0x1169, 0x1161, 0x116a),	// ㅘ
        listOf(0x1169, 0x1162, 0x116b),	// ㅙ
        listOf(0x1169, 0x1175, 0x116c),	// ㅚ
        listOf(0x116e, 0x1165, 0x116f),	// ㅝ
        listOf(0x116e, 0x1166, 0x1170),	// ㅞ
        listOf(0x116e, 0x1175, 0x1171),	// ㅟ
        listOf(0x1173, 0x1175, 0x1174),	// ㅢ

        listOf(0x11a8, 0x11ba, 0x11aa),	// ㄳ
        listOf(0x11ab, 0x11bd, 0x11ac),	// ㄵ
        listOf(0x11ab, 0x11c2, 0x11ad),	// ㄶ
        listOf(0x11af, 0x11a8, 0x11b0),	// ㄺ
        listOf(0x11af, 0x11b7, 0x11b1),	// ㄻ
        listOf(0x11af, 0x11b8, 0x11b2),	// ㄼ
        listOf(0x11af, 0x11ba, 0x11b3),	// ㄽ
        listOf(0x11af, 0x11c0, 0x11b4),	// ㄾ
        listOf(0x11af, 0x11c1, 0x11b5),	// ㄿ
        listOf(0x11af, 0x11c2, 0x11b6),	// ㅀ
        listOf(0x11b8, 0x11ba, 0x11b9),	// ㅄ
    )

}