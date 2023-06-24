package ee.oyatl.ime.f.common

abstract class DefaultTableIME: DefaultFboardIME(), TableIME {

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