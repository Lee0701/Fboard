package ee.oyatl.ime.f.core.input

enum class CustomKeycode(val code: Int) {

    KEYCODE_COMMA_PERIOD(0x102c),
    KEYCODE_PERIOD_COMMA(0x102e),

    KEYCODE_3SET_390_0(0x1030),
    KEYCODE_3SET_390_1(0x1031),
    KEYCODE_3SET_390_2(0x1032),
    KEYCODE_3SET_390_3(0x1033),

    KEYCODE_3SET_390_10(0x1130),
    KEYCODE_3SET_390_11(0x1131),
    ;

    companion object {
        val codes = entries.map { it.code }
    }

}