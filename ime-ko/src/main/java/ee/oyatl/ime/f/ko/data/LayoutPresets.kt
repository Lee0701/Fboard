package ee.oyatl.ime.f.ko.data

import ee.oyatl.ime.f.common.data.SoftKeyboardLayouts.LAYOUT_QWERTY_MOBILE
import ee.oyatl.ime.f.core.table.CodeConvertTable
import ee.oyatl.ime.f.core.table.SimpleCodeConvertTable
import ee.oyatl.ime.f.ko.hangul.JamoCombinationTable

object LayoutPresets {

    val SOFT_LAYOUT_PRESET_MOBILE = mapOf(
        "qwerty" to LAYOUT_QWERTY_MOBILE,
        "qwerty_390" to SoftKeyboardLayouts.LAYOUT_QWERTY_390_MOBILE,
        "qwerty_391" to SoftKeyboardLayouts.LAYOUT_QWERTY_391_MOBILE,
    )

    val PRESET_2SET_KS = Entry(
        "qwerty",
        HangulTables.LAYOUT_2SET_KS,
        HangulTables.COMB_2SET_KS,
    )

    val PRESET_3SET_390 = Entry(
        "qwerty_390",
        HangulTables.LAYOUT_HANGUL_3SET_390,
        HangulTables.COMB_3SET_390,
    )

    val PRESET_3SET_391 = Entry(
        "qwerty_391",
        HangulTables.LAYOUT_HANGUL_3SET_391,
        HangulTables.COMB_3SET_391,
    )

    val IME_PRESET: Map<String, Entry> = mapOf(
        "2set_ks" to PRESET_2SET_KS,
        "3set_390" to PRESET_3SET_390,
        "3set_391" to PRESET_3SET_391,
    )

    data class Entry(
        val softKeyboardPreset: String = "qwerty",
        val convertTable: CodeConvertTable = SimpleCodeConvertTable(),
        val combinationTable: JamoCombinationTable = JamoCombinationTable(),
    )
}