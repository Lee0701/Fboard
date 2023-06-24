package ee.oyatl.ime.f.core.table

import ee.oyatl.ime.f.core.input.ModifierState

sealed interface CodeConvertTable {
    fun getAllForState(state: ModifierState): Map<Int, Int>
    fun getReversed(charCode: Int, state: ModifierState): Int?

    operator fun get(keyCode: Int, state: ModifierState): Int?
    operator fun plus(table: CodeConvertTable): CodeConvertTable
}