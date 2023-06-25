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

    val COMB_3SET_CHO = JamoCombinationTable(
        listOf(0x1100, 0x1100, 0x1101),	// ㄲ
        listOf(0x1103, 0x1103, 0x1104),	// ㄸ
        listOf(0x1107, 0x1107, 0x1108),	// ㅃ
        listOf(0x1109, 0x1109, 0x110a),	// ㅆ
        listOf(0x110c, 0x110c, 0x110d),	// ㅉ
    )

    val COMB_3SET_JUNG = JamoCombinationTable(
        listOf(0x1169, 0x1161, 0x116a),	// ㅘ
        listOf(0x1169, 0x1162, 0x116b),	// ㅙ
        listOf(0x1169, 0x1175, 0x116c),	// ㅚ
        listOf(0x116e, 0x1165, 0x116f),	// ㅝ
        listOf(0x116e, 0x1166, 0x1170),	// ㅞ
        listOf(0x116e, 0x1175, 0x1171),	// ㅟ
        listOf(0x1173, 0x1175, 0x1174),	// ㅢ
    )

    val COMB_3SET_JONG = JamoCombinationTable(
        listOf(0x11a8, 0x11a8, 0x11a9),	// ㄲ
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
        listOf(0x11ba, 0x11ba, 0x11bb),	// ㅆ
    )

    val LAYOUT_HANGUL_3SET_390 = SimpleCodeConvertTable(
        KeyEvent.KEYCODE_1 to Entry(0x11c2, 0x11bd),
        KeyEvent.KEYCODE_2 to Entry(0x11bb, 0x0040),
        KeyEvent.KEYCODE_3 to Entry(0x11b8, 0x0023),
        KeyEvent.KEYCODE_4 to Entry(0x116d, 0x0024),
        KeyEvent.KEYCODE_5 to Entry(0x1172, 0x0025),
        KeyEvent.KEYCODE_6 to Entry(0x1163, 0x005e),
        KeyEvent.KEYCODE_7 to Entry(0x1168, 0x0026),
        KeyEvent.KEYCODE_8 to Entry(0x1174, 0x002a),
        KeyEvent.KEYCODE_9 to Entry(0x116e, 0x0028),
        KeyEvent.KEYCODE_0 to Entry(0x110f, 0x0029),

        KeyEvent.KEYCODE_Q to Entry(0x11ba, 0x11c1),
        KeyEvent.KEYCODE_W to Entry(0x11af, 0x11c0),
        KeyEvent.KEYCODE_E to Entry(0x1167, 0x11bf),
        KeyEvent.KEYCODE_R to Entry(0x1162, 0x1164),
        KeyEvent.KEYCODE_T to Entry(0x1165, 0x003b),
        KeyEvent.KEYCODE_Y to Entry(0x1105, 0x003c),
        KeyEvent.KEYCODE_U to Entry(0x1103, 0x0037),
        KeyEvent.KEYCODE_I to Entry(0x1106, 0x0038),
        KeyEvent.KEYCODE_O to Entry(0x110e, 0x0039),
        KeyEvent.KEYCODE_P to Entry(0x1111, 0x003e),

        KeyEvent.KEYCODE_A to Entry(0x11bc, 0x11ae),
        KeyEvent.KEYCODE_S to Entry(0x11ab, 0x11ad),
        KeyEvent.KEYCODE_D to Entry(0x1175, 0x11b0),
        KeyEvent.KEYCODE_F to Entry(0x1161, 0x11a9),
        KeyEvent.KEYCODE_G to Entry(0x1173, 0x002f),
        KeyEvent.KEYCODE_H to Entry(0x1102, 0x0027),
        KeyEvent.KEYCODE_J to Entry(0x110b, 0x0034),
        KeyEvent.KEYCODE_K to Entry(0x1100, 0x0035),
        KeyEvent.KEYCODE_L to Entry(0x110c, 0x0036),
        KeyEvent.KEYCODE_SEMICOLON to Entry(0x1107, 0x003a),
        KeyEvent.KEYCODE_APOSTROPHE to Entry(0x1110, 0x0022),

        KeyEvent.KEYCODE_Z to Entry(0x11b7, 0x11be),
        KeyEvent.KEYCODE_X to Entry(0x11a8, 0x11b9),
        KeyEvent.KEYCODE_C to Entry(0x1166, 0x11b1),
        KeyEvent.KEYCODE_V to Entry(0x1169, 0x11b6),
        KeyEvent.KEYCODE_B to Entry(0x116e, 0x0021),
        KeyEvent.KEYCODE_N to Entry(0x1109, 0x0030),
        KeyEvent.KEYCODE_M to Entry(0x1112, 0x0031),
        KeyEvent.KEYCODE_COMMA to Entry(0x002c, 0x0032),
        KeyEvent.KEYCODE_PERIOD to Entry(0x002e, 0x0033),
        KeyEvent.KEYCODE_SLASH to Entry(0x1169, 0x003f),

        CustomKeycode.KEYCODE_3SET_390_0.code to Entry(0x116e, 0x0030),
        CustomKeycode.KEYCODE_3SET_390_1.code to Entry(0x1109, 0x0031),
        CustomKeycode.KEYCODE_3SET_390_2.code to Entry(0x1112, 0x0032),
        CustomKeycode.KEYCODE_3SET_390_3.code to Entry(0x1110, 0x0033),
    ) + LAYOUT_CUSTOM_KEYS

    val COMB_3SET_390 = COMB_3SET_CHO + COMB_3SET_JUNG + COMB_3SET_JONG

    val LAYOUT_HANGUL_3SET_391 = SimpleCodeConvertTable(
        KeyEvent.KEYCODE_1 to Entry(0x11c2, 0x11a9), // ᇂ, ᆩ
        KeyEvent.KEYCODE_2 to Entry(0x11bb, 0x11b0), // ᆻ, ᆰ
        KeyEvent.KEYCODE_3 to Entry(0x11b8, 0x11bd), // ᆸ, ᆽ
        KeyEvent.KEYCODE_4 to Entry(0x116d, 0x11b5), // ᅭ, ᆵ
        KeyEvent.KEYCODE_5 to Entry(0x1172, 0x11b4), // ᅲ, ᆴ
        KeyEvent.KEYCODE_6 to Entry(0x1163, 0x003d), // ᅣ, =
        KeyEvent.KEYCODE_7 to Entry(0x1168, 0x201c), // ᅨ, “
        KeyEvent.KEYCODE_8 to Entry(0x1174, 0x201d), // ᅴ, ”
        KeyEvent.KEYCODE_9 to Entry(0x116e, 0x0027), // ᅮ, '
        KeyEvent.KEYCODE_0 to Entry(0x110f, 0x007e), // ᄏ, ~
        KeyEvent.KEYCODE_Q to Entry(0x11ba, 0x11c1), // ᆺ, ᇁ
        KeyEvent.KEYCODE_W to Entry(0x11af, 0x11c0), // ᆯ, ᇀ
        KeyEvent.KEYCODE_E to Entry(0x1167, 0x11ac), // ᅧ, ᆬ
        KeyEvent.KEYCODE_R to Entry(0x1162, 0x11b6), // ᅢ, ᆶ
        KeyEvent.KEYCODE_T to Entry(0x1165, 0x11b3), // ᅥ, ᆳ
        KeyEvent.KEYCODE_Y to Entry(0x1105, 0x0035), // ᄅ, 5
        KeyEvent.KEYCODE_U to Entry(0x1103, 0x0036), // ᄃ, 6
        KeyEvent.KEYCODE_I to Entry(0x1106, 0x0037), // ᄆ, 7
        KeyEvent.KEYCODE_O to Entry(0x110e, 0x0038), // ᄎ, 8
        KeyEvent.KEYCODE_P to Entry(0x1111, 0x0039), // ᄑ, 9
        KeyEvent.KEYCODE_A to Entry(0x11bc, 0x11ae), // ᆼ, ᆮ
        KeyEvent.KEYCODE_S to Entry(0x11ab, 0x11ad), // ᆫ, ᆭ
        KeyEvent.KEYCODE_D to Entry(0x1175, 0x11b2), // ᅵ, ᆲ
        KeyEvent.KEYCODE_F to Entry(0x1161, 0x11b1), // ᅡ, ᆱ
        KeyEvent.KEYCODE_G to Entry(0x1173, 0x1164), // ᅳ, ᅤ
        KeyEvent.KEYCODE_H to Entry(0x1102, 0x0030), // ᄂ, 0
        KeyEvent.KEYCODE_J to Entry(0x110b, 0x0031), // ᄋ, 1
        KeyEvent.KEYCODE_K to Entry(0x1100, 0x0032), // ᄀ, 2
        KeyEvent.KEYCODE_L to Entry(0x110c, 0x0033), // ᄌ, 3
        KeyEvent.KEYCODE_SEMICOLON to Entry(0x1107, 0x0034), // ᄇ, 4
        KeyEvent.KEYCODE_APOSTROPHE to Entry(0x1110, 0x00b7), // ᄐ, ·
        KeyEvent.KEYCODE_Z to Entry(0x11b7, 0x11be), // ᆷ, ᆾ
        KeyEvent.KEYCODE_X to Entry(0x11a8, 0x11b9), // ᆨ, ᆹ
        KeyEvent.KEYCODE_C to Entry(0x1166, 0x11bf), // ᅦ, ᆿ
        KeyEvent.KEYCODE_V to Entry(0x1169, 0x11aa), // ᅩ, ᆪ
        KeyEvent.KEYCODE_B to Entry(0x116e, 0x003f), // ᅮ, ?
        KeyEvent.KEYCODE_N to Entry(0x1109, 0x002d), // ᄉ, -
        KeyEvent.KEYCODE_M to Entry(0x1112, 0x0022), // ᄒ, "
        KeyEvent.KEYCODE_COMMA to Entry(0x002c, 0x002c), // ,, ,
        KeyEvent.KEYCODE_PERIOD to Entry(0x002e, 0x002e), // ., .
        KeyEvent.KEYCODE_SLASH to Entry(0x1169, 0x0021), // ᅩ, !
        KeyEvent.KEYCODE_MINUS to Entry(0x0029, 0x003b), // ), ;
        KeyEvent.KEYCODE_NUM to Entry(0x003e, 0x002b), // >, +
        KeyEvent.KEYCODE_LEFT_BRACKET to Entry(0x0028, 0x0025), // (, %
        KeyEvent.KEYCODE_RIGHT_BRACKET to Entry(0x003c, 0x002f), // <, /
        KeyEvent.KEYCODE_BACKSLASH to Entry(0x003a, 0x005c), // :, \
        KeyEvent.KEYCODE_GRAVE to Entry(0x002a, 0x203b), // *, ※
    ) + LAYOUT_CUSTOM_KEYS

    val LAYOUT_HANGUL_3SET_391_STRICT = LAYOUT_HANGUL_3SET_391 + SimpleCodeConvertTable(
        KeyEvent.KEYCODE_9 to Entry(0x100116e, 0x0027), // ᅮ, '
        KeyEvent.KEYCODE_SLASH to Entry(0x1001169, 0x0021), // ᅩ, !
    )

    val COMB_3SET_391 = COMB_3SET_CHO + COMB_3SET_JUNG + COMB_3SET_JONG

    val COMB_SEBEOL_391_STRICT = COMB_3SET_CHO + JamoCombinationTable(
        listOf(0x1001169, 0x1161, 0x116a),	// ㅘ
        listOf(0x1001169, 0x1162, 0x116b),	// ㅙ
        listOf(0x1001169, 0x1175, 0x116c),	// ㅚ
        listOf(0x100116e, 0x1165, 0x116f),	// ㅝ
        listOf(0x100116e, 0x1166, 0x1170),	// ㅞ
        listOf(0x100116e, 0x1175, 0x1171),	// ㅟ
    )

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

    val COMB_2SET_KS: JamoCombinationTable = JamoCombinationTable(
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