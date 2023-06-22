package ee.oyatl.ime.f.common.view.model

data class Row(
    val keys: List<RowItem> = listOf(),
) {
    constructor(vararg keys: RowItem): this(keys.toList())
}