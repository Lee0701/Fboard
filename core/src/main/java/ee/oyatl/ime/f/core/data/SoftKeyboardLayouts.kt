package ee.oyatl.ime.f.core.data

import android.view.KeyEvent
import ee.oyatl.ime.f.core.R
import ee.oyatl.ime.f.core.model.*

object SoftKeyboardLayouts {
    val ROW_BOTTOM = Row(listOf(
        Key(KeyEvent.KEYCODE_SYM, null, "?12", width = 1.5f, type = KeyType.Modifier),
        Key(KeyEvent.KEYCODE_COMMA, ",", type = KeyType.AlphanumericAlt),
        Key(KeyEvent.KEYCODE_LANGUAGE_SWITCH, null, iconType = KeyIconType.Language, type = KeyType.ModifierAlt),
        Key(KeyEvent.KEYCODE_SPACE, null, "", width = 4f, type = KeyType.Space),
        Key(KeyEvent.KEYCODE_PERIOD, ".", type = KeyType.AlphanumericAlt),
        Key(KeyEvent.KEYCODE_ENTER, null, iconType = KeyIconType.Return, width = 1.5f, type = KeyType.Action),
    ))

    val ROW_NUMBERS = Row(listOf(
        Key(KeyEvent.KEYCODE_1, "1"),
        Key(KeyEvent.KEYCODE_2, "2"),
        Key(KeyEvent.KEYCODE_3, "3"),
        Key(KeyEvent.KEYCODE_4, "4"),
        Key(KeyEvent.KEYCODE_5, "5"),
        Key(KeyEvent.KEYCODE_6, "6"),
        Key(KeyEvent.KEYCODE_7, "7"),
        Key(KeyEvent.KEYCODE_8, "8"),
        Key(KeyEvent.KEYCODE_9, "9"),
        Key(KeyEvent.KEYCODE_0, "0"),
    ))

    val LAYOUT_QWERTY_MOBILE = Keyboard(listOf(
        Row(listOf(
            Key(KeyEvent.KEYCODE_Q, "Q"),
            Key(KeyEvent.KEYCODE_W, "W"),
            Key(KeyEvent.KEYCODE_E, "E"),
            Key(KeyEvent.KEYCODE_R, "R"),
            Key(KeyEvent.KEYCODE_T, "T"),
            Key(KeyEvent.KEYCODE_Y, "Y"),
            Key(KeyEvent.KEYCODE_U, "U"),
            Key(KeyEvent.KEYCODE_I, "I"),
            Key(KeyEvent.KEYCODE_O, "O"),
            Key(KeyEvent.KEYCODE_P, "P"),
        )),
        Row(listOf(
            Spacer(0.5f),
            Key(KeyEvent.KEYCODE_A, "A"),
            Key(KeyEvent.KEYCODE_S, "S"),
            Key(KeyEvent.KEYCODE_D, "D"),
            Key(KeyEvent.KEYCODE_F, "F"),
            Key(KeyEvent.KEYCODE_G, "G"),
            Key(KeyEvent.KEYCODE_H, "H"),
            Key(KeyEvent.KEYCODE_J, "J"),
            Key(KeyEvent.KEYCODE_K, "K"),
            Key(KeyEvent.KEYCODE_L, "L"),
            Spacer(0.5f),
        )),
        Row(listOf(
            Key(KeyEvent.KEYCODE_SHIFT_LEFT, null, iconType = KeyIconType.Shift, width = 1.5f, type = KeyType.Modifier),
            Key(KeyEvent.KEYCODE_Z, "Z"),
            Key(KeyEvent.KEYCODE_X, "X"),
            Key(KeyEvent.KEYCODE_C, "C"),
            Key(KeyEvent.KEYCODE_V, "V"),
            Key(KeyEvent.KEYCODE_B, "B"),
            Key(KeyEvent.KEYCODE_N, "N"),
            Key(KeyEvent.KEYCODE_M, "M"),
            Key(KeyEvent.KEYCODE_DEL, null, iconType = KeyIconType.Backspace, width = 1.5f, repeatable = true, type = KeyType.Modifier),
        )),
        ROW_BOTTOM,
    ), 220f)

}