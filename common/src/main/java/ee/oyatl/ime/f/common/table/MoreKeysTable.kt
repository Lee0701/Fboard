package ee.oyatl.ime.f.common.table

import ee.oyatl.ime.f.common.view.model.KeyboardLayout

data class MoreKeysTable(
    val map: Map<Int, KeyboardLayout> = mapOf(),
) {
    constructor(vararg entries: Pair<Int, KeyboardLayout>): this(entries.toMap())

    operator fun plus(another: MoreKeysTable): MoreKeysTable {
        return MoreKeysTable(this.map + another.map)
    }

    operator fun get(keyCode: Int): KeyboardLayout? {
        return map[keyCode]
    }
}