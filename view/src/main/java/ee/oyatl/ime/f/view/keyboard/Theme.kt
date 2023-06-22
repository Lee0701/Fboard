package ee.oyatl.ime.f.view.keyboard

import ee.oyatl.ime.f.core.model.KeyIconType
import ee.oyatl.ime.f.core.model.KeyType

data class Theme(
    val keyboardBackground: Int,
    val keyBackground: Map<KeyType, Int> = mapOf(),
    val keyIcon: Map<KeyIconType, Int> = mapOf(),
    val popupBackground: Int,
)