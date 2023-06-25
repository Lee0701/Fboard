package ee.oyatl.ime.f.ko.data

import android.view.KeyEvent
import ee.oyatl.ime.f.common.data.SoftKeyboardLayouts
import ee.oyatl.ime.f.common.view.model.Key
import ee.oyatl.ime.f.common.view.model.KeyIconType
import ee.oyatl.ime.f.common.view.model.KeyType
import ee.oyatl.ime.f.common.view.model.KeyboardLayout
import ee.oyatl.ime.f.common.view.model.Row
import ee.oyatl.ime.f.core.input.CustomKeycode

object SoftKeyboardLayouts {

    val ROW_390_391_BOTTOM = Row(
        Key(KeyEvent.KEYCODE_SYM, null, iconType = KeyIconType.Symbol, width = 1.5f, type = KeyType.Modifier),
        Key(CustomKeycode.KEYCODE_PERIOD_COMMA.code, ".", type = KeyType.AlphanumericAlt),
        Key(KeyEvent.KEYCODE_LANGUAGE_SWITCH, null, iconType = KeyIconType.Language, type = KeyType.ModifierAlt),
        Key(KeyEvent.KEYCODE_SPACE, null, "", width = 4f, type = KeyType.Space),
        Key(KeyEvent.KEYCODE_SLASH, "/", type = KeyType.AlphanumericAlt),
        Key(KeyEvent.KEYCODE_ENTER, null, iconType = KeyIconType.Return, width = 1.5f, type = KeyType.Action),
    )

    val ROW_QWERTY_390_3 = Row(
        Key(KeyEvent.KEYCODE_SHIFT_LEFT, null, iconType = KeyIconType.Shift, type = KeyType.Modifier),
        Key(KeyEvent.KEYCODE_Z, "Z"),
        Key(KeyEvent.KEYCODE_X, "X"),
        Key(KeyEvent.KEYCODE_C, "C"),
        Key(KeyEvent.KEYCODE_V, "V"),
        Key(CustomKeycode.KEYCODE_3SET_390_0.code, "B"),
        Key(CustomKeycode.KEYCODE_3SET_390_1.code, "N"),
        Key(CustomKeycode.KEYCODE_3SET_390_2.code, "M"),
        Key(CustomKeycode.KEYCODE_3SET_390_3.code, "'"),
        Key(KeyEvent.KEYCODE_DEL, null, iconType = KeyIconType.Backspace, repeatable = true, type = KeyType.Modifier),
    )

    val ROW_QWERTY_391_3 = Row(
        Key(KeyEvent.KEYCODE_SHIFT_LEFT, null, iconType = KeyIconType.Shift, type = KeyType.Modifier),
        Key(KeyEvent.KEYCODE_Z, "Z"),
        Key(KeyEvent.KEYCODE_X, "X"),
        Key(KeyEvent.KEYCODE_C, "C"),
        Key(KeyEvent.KEYCODE_V, "V"),
        Key(KeyEvent.KEYCODE_B, "B"),
        Key(KeyEvent.KEYCODE_N, "N"),
        Key(KeyEvent.KEYCODE_M, "M"),
        Key(KeyEvent.KEYCODE_APOSTROPHE, "'"),
        Key(KeyEvent.KEYCODE_DEL, null, iconType = KeyIconType.Backspace, repeatable = true, type = KeyType.Modifier),
    )

    val LAYOUT_QWERTY_390_MOBILE = KeyboardLayout(
        SoftKeyboardLayouts.ROW_NUMBERS,
        SoftKeyboardLayouts.ROW_QWERTY_1,
        SoftKeyboardLayouts.ROW_QWERTY_2_WO_SPACER + Row(SoftKeyboardLayouts.SEMICOLON),
        ROW_QWERTY_390_3,
        ROW_390_391_BOTTOM,
    )

    val LAYOUT_QWERTY_391_MOBILE = KeyboardLayout(
        SoftKeyboardLayouts.ROW_NUMBERS,
        SoftKeyboardLayouts.ROW_QWERTY_1,
        SoftKeyboardLayouts.ROW_QWERTY_2_WO_SPACER + Row(SoftKeyboardLayouts.SEMICOLON),
        ROW_QWERTY_391_3,
        ROW_390_391_BOTTOM,
    )

}