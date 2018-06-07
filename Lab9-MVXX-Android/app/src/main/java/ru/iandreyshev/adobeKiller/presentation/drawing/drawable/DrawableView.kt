package ru.iandreyshev.adobeKiller.presentation.drawing.drawable

import ru.iandreyshev.adobeKiller.presentation.drawing.ICanvas

abstract class DrawableView : IDrawable {

    protected val width: Float
        get() = frame.width
    protected val height: Float
        get() = frame.height

    var onSelectListener: (IDrawable) -> Unit = {}
    var onDeleteListener: (IDrawable) -> Unit = {}

    final override fun draw(canvas: ICanvas) {
        onDrawShape(canvas)
        canvas.color = style.fillColor
        canvas.fill()

        onDrawStroke(canvas)
        canvas.color = style.strokeColor
        canvas.strokeSize = style.strokeSize
        canvas.stroke()
    }

    protected abstract fun onDrawShape(canvas: ICanvas)

    protected abstract fun onDrawStroke(canvas: ICanvas)

    final override fun onSelect() =
            onSelectListener(this)

    final override fun onDelete() =
            onDeleteListener(this)

}
