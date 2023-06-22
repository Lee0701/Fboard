package ee.oyatl.ime.f.view

import android.content.Context
import android.util.TypedValue
import android.view.View
import ee.oyatl.ime.f.core.data.SoftKeyboardLayouts

interface InputViewManager {
    val keyboardListener: KeyboardListener

    fun createInputView(context: Context): View? {
        val rowHeight = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            52f,
            context.resources.displayMetrics
        ).toInt()
        val keyboardView = StackedViewKeyboardView(
            context,
            null,
            SoftKeyboardLayouts.LAYOUT_QWERTY_MOBILE,
            Themes.Dynamic,
            keyboardListener,
            false,
            rowHeight,
        )
        return keyboardView
    }

}