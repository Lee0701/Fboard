package ee.oyatl.ime.f.common.input

import ee.oyatl.ime.f.common.ModifierState

sealed interface CodeConvertTable {
    fun getAllForState(state: ModifierState): Map<Int, Int>
    fun getReversed(charCode: Int, entryKey: SimpleCodeConvertTable.EntryKey): Int?

    operator fun get(keyCode: Int, state: ModifierState): Int?
    operator fun plus(table: CodeConvertTable): CodeConvertTable
}