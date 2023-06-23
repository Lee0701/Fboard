package ee.oyatl.ime.f.core.table

import ee.oyatl.ime.f.core.input.ModifierState

class CharOverrideTable(
    val map: Map<Int, Int> = mapOf(),
) {

    private val reversedMap: Map<Int, Int> = map.map { (key, value) ->
        value to key
    }.toMap()

    fun getAllForState(state: ModifierState): Map<Int, Int> {
        return map
    }

    fun getReversed(charCode: Int): Int? {
        return reversedMap[charCode]
    }

    operator fun get(charCode: Int): Int? {
        return map[charCode]
    }

    operator fun plus(table: CharOverrideTable): CharOverrideTable {
        return CharOverrideTable(map = this.map + table.map)
    }
}