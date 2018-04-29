package ru.iandreyshev.compositeshapespaint.ui.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

open class CanvasView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : TouchHandlerView(context, attrs, defStyleAttr) {

    private var mDrawAction: ((View, Canvas) -> Unit)? = null

    fun onDrawAction(action: (Canvas) -> Unit) {
        mDrawAction = { _, canvas -> action(canvas) }
    }

    fun onDrawAction(action: (View, Canvas) -> Unit) {
        mDrawAction = action
    }

    override fun onDraw(canvas: Canvas) {
        mDrawAction?.invoke(this, canvas)
    }
}
