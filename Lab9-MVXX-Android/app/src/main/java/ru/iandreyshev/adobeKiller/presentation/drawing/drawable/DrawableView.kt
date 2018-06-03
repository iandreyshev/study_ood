package ru.iandreyshev.adobeKiller.presentation.drawing.drawable

import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.ICanvas
import ru.iandreyshev.adobeKiller.presentation.drawing.style.Style

abstract class DrawableView(
        override val style: Style
) : IDrawable {

    protected val width: Float
        get() = frame.width
    protected val height: Float
        get() = frame.height

    private var mOnClickListener: () -> Unit = {}
    private var mOnDeleteListener: () -> Unit = {}

    final override fun draw(canvas: ICanvas) {
        onDrawShape(canvas)
        canvas.color = style.fillColor
        canvas.fill()

        onDrawStroke(canvas)
        canvas.color = style.strokeColor
        canvas.strokeSize = style.strokeSize
        canvas.stroke()
    }

    fun setOnClickListener(listener: () -> Unit) {
        mOnClickListener = listener
    }

    fun setOnDeleteListener(listener: () -> Unit) {
        mOnDeleteListener = listener
    }

    protected abstract fun onDrawShape(canvas: ICanvas)

    protected abstract fun onDrawStroke(canvas: ICanvas)

    final override fun onClick() {
        mOnClickListener()
    }

    final override fun onDelete() {
        mOnDeleteListener()
    }

}
