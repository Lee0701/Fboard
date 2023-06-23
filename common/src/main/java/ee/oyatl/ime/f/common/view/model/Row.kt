package ee.oyatl.ime.f.common.view.model

data class Row(
    val keys: List<RowItem> = listOf(),
) {
    constructor(vararg keys: RowItem): this(keys.toList())

    operator fun plus(another: Row): Row {
        return Row(
            keys = this.keys + another.keys,
        )
    }
}