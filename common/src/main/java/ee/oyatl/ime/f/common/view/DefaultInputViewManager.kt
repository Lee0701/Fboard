package ee.oyatl.ime.f.common.view

import android.content.Context
import android.util.TypedValue
import android.view.View
import ee.oyatl.ime.f.common.view.keyboard.KeyboardListener
import ee.oyatl.ime.f.common.view.keyboard.StackedViewKeyboardView

class DefaultInputViewManager(
    override val keyboardListener: KeyboardListener,
    override val params: InputViewManager.Params,
): InputViewManager {

    override fun createInputView(context: Context): View {
        val rowHeight = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            params.rowHeight.toFloat(),
            context.resources.displayMetrics
        ).toInt()
        val keyboardView = StackedViewKeyboardView(
            context, null,
            keyboard = params.keyboardLayout,
            theme = params.keyboardTheme,
            listener = keyboardListener,
            unifyHeight = params.unifyHeight,
            rowHeight = rowHeight,
        )
        return keyboardView
    }

}