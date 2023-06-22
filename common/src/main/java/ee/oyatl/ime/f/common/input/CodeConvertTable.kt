package ee.oyatl.ime.f.common.input

sealed interface CodeConvertTable {
    fun get(keyCode: Int, state: KeyboardState): Int?
    fun getAllForState(state: KeyboardState): Map<Int, Int>
    fun getReversed(charCode: Int, entryKey: SimpleCodeConvertTable.EntryKey): Int?

    operator fun plus(table: CodeConvertTable): CodeConvertTable
}