package ee.oyatl.ime.f.common

import android.graphics.drawable.Drawable
import android.view.KeyEvent

abstract class DefaultTableIME: DefaultFboardIME(), TableIME {

    private val labelsRange = KeyEvent.KEYCODE_UNKNOWN .. KeyEvent.KEYCODE_SEARCH
    private val defaultLabels: Map<Int, String> get() = labelsRange.associateWith { code ->
        keyCharacterMap.get(code, modifierState.asMetaState()).toChar().toString()
    }

    override fun updateLabelsAndIcons() {
        val labels = convertTable
            .getAllForState(modifierState)
            .mapValues { (key, value) -> value.toChar().toString() }
        val icons = mapOf<Int, Drawable>()

        inputViewManager.updateLabelsAndIcons(
            labels = defaultLabels + labels,
            icons = icons,
        )
    }

    override fun onPrintingKey(keyCode: Int): Boolean {
        val inputConnection = currentInputConnection ?: return false
        val converted = convertTable[keyCode, modifierState]
        if(converted == null) {
            val char = keyCharacterMap.get(keyCode, modifierState.asMetaState())
            onReset()
            if(char > 0) inputConnection.commitText(char.toChar().toString(), 1)
        } else {
            val override = overrideTable[converted]
            val text = (override ?: converted).toChar().toString()
            inputConnection.commitText(text, 1)
        }
        onUpdate()
        return true
    }

}