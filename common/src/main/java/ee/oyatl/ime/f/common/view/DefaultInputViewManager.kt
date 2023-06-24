package ee.oyatl.ime.f.common.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.View
import ee.oyatl.ime.f.common.table.MoreKeysTable
import ee.oyatl.ime.f.common.view.keyboard.KeyboardListener
import ee.oyatl.ime.f.common.view.keyboard.KeyboardView
import ee.oyatl.ime.f.common.view.keyboard.StackedViewKeyboardView

class DefaultInputViewManager(
    override val keyboardListener: KeyboardListener,
    override val params: InputViewManager.Params,
): InputViewManager {
    var keyboardView: KeyboardView? = null

    override fun createView(context: Context): View {
        val rowHeight = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            params.rowHeight.toFloat(),
            context.resources.displayMetrics
        ).toInt()
        val keyboardView = StackedViewKeyboardView(
            context, null,
            listener = keyboardListener,
            keyboard = params.keyboardLayout,
            theme = params.keyboardTheme,
            unifyHeight = params.unifyHeight,
            rowHeight = rowHeight,
        )
        this.keyboardView = keyboardView
        return keyboardView
    }

    override fun updateLabelsAndIcons(labels: Map<Int, CharSequence>, icons: Map<Int, Drawable>) {
        keyboardView?.updateLabelsAndIcons(labels, icons)
    }

}