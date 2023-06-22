package ee.oyatl.ime.f.view.keyboard

import ee.oyatl.ime.f.view.model.KeyIconType
import ee.oyatl.ime.f.view.model.KeyType

data class Theme(
    val keyboardBackground: Int,
    val keyBackground: Map<KeyType, Int> = mapOf(),
    val keyIcon: Map<KeyIconType, Int> = mapOf(),
    val popupBackground: Int,
)