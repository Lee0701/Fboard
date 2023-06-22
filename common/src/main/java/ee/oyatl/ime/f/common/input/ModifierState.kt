package ee.oyatl.ime.f.common.input

data class ModifierState(
    val pressed: Boolean = false,
    val locked: Boolean = false,
    val pressing: Boolean = pressed,
)