package ee.oyatl.ime.f.core.model

data class KeyboardLayout(
    val rows: List<Row> = listOf(),
    val height: Float = 1f,
) {
    operator fun plus(another: KeyboardLayout): KeyboardLayout {
        return KeyboardLayout(
            rows = this.rows + another.rows,
            height = (this.height + another.height) / 2f,
        )
    }
}