package ee.oyatl.ime.f.core.input

import android.view.KeyEvent

data class ModifierState(
    val shiftState: Item = Item(),
    val altState: Item = Item(),
    val controlState: Item = Item(),
    val metaState: Item = Item(),
) {
    fun asMetaState(): Int {
        var result = 0
        result = result or if(shiftState.pressed || shiftState.pressing) KeyEvent.META_SHIFT_ON else 0
        result = result or if(altState.pressed || altState.pressing) KeyEvent.META_ALT_ON else 0
        result = result or if(controlState.pressed || controlState.pressing) KeyEvent.META_CTRL_ON else 0
        result = result or if(metaState.pressed || metaState.pressing) KeyEvent.META_META_ON else 0
        return result
    }
    data class Item(
        val pressed: Boolean = false,
        val locked: Boolean = false,
        val pressing: Boolean = pressed,
    )
}
