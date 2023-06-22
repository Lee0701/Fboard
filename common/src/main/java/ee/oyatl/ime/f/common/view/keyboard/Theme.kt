package ee.oyatl.ime.f.common.view.keyboard

import ee.oyatl.ime.f.common.view.model.KeyIconType
import ee.oyatl.ime.f.common.view.model.KeyType

data class Theme(
    val keyboardBackground: Int,
    val keyBackground: Map<KeyType, Int> = mapOf(),
    val keyIcon: Map<KeyIconType, Int> = mapOf(),
    val popupBackground: Int,
)