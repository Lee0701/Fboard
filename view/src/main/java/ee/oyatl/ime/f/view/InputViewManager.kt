package ee.oyatl.ime.f.view

import android.content.Context
import android.view.View
import ee.oyatl.ime.f.view.model.KeyboardLayout
import ee.oyatl.ime.f.view.keyboard.KeyboardListener
import ee.oyatl.ime.f.view.keyboard.Theme

interface InputViewManager {
    val keyboardListener: KeyboardListener
    val params: Params

    fun createInputView(context: Context): View

    data class Params(
        val keyboardLayout: KeyboardLayout,
        val keyboardTheme: Theme,
        val unifyHeight: Boolean,
        val rowHeight: Int,
    )
}