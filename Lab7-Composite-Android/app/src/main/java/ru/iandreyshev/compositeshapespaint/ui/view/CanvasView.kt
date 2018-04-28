package ru.iandreyshev.compositeshapespaint.ui.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

class CanvasView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : TouchHandlerView(context, attrs, defStyleAttr) {
    private var mPainter: ((View, Canvas) -> Unit)? = null

    fun onDrawAction(action: (Canvas) -> Unit) {
        mPainter = { _, canvas -> action(canvas) }
    }

    fun onDrawAction(action: (View, Canvas) -> Unit) {
        mPainter = action
    }

    override fun onDraw(canvas: Canvas) {
        mPainter?.invoke(this, canvas)
    }
}
