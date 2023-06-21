package ee.oyatl.ime.f.core.model

data class Keyboard(
    val rows: List<Row> = listOf(),
    val height: Float = 200f,
) {
    operator fun plus(another: Keyboard): Keyboard {
        return Keyboard(
            rows = this.rows + another.rows,
            height = this.height + another.height,
        )
    }
}