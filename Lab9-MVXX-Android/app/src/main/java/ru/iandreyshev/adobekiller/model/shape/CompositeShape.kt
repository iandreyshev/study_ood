package ru.iandreyshev.adobekiller.model.shape

import ru.iandreyshev.adobekiller.model.canvas.ICanvas
import ru.iandreyshev.adobekiller.model.extension.ISimpleIterator
import ru.iandreyshev.adobekiller.model.shape.frame.CompositeFrame
import ru.iandreyshev.adobekiller.model.shape.frame.IFrame
import ru.iandreyshev.adobekiller.model.shape.style.CompositeStyle
import ru.iandreyshev.adobekiller.model.shape.style.IStyle

class CompositeShape(
        override val name: String,
        shapes: List<IShape>
) : ICompositeShape {

    constructor(name: String, vararg shape: IShape) : this(name, listOf(*shape))

    private val mShapes: MutableList<IShape> = ArrayList(shapes)

    override val composite: ICompositeShape? = this

    override var frame: IFrame = CompositeFrame(FramesIterator())
        private set

    override var style: IStyle = CompositeStyle(StylesIterator())
        private set

    override fun add(shape: IShape) {
        mShapes.add(shape)

        frame = CompositeFrame(FramesIterator())
        style = CompositeStyle(StylesIterator())
    }

    override fun remove(shape: IShape) {
        mShapes.remove(shape)

        frame = CompositeFrame(FramesIterator())
        style = CompositeStyle(StylesIterator())
    }

    override fun draw(canvas: ICanvas) =
            mShapes.forEach { it.draw(canvas) }

    private inner class FramesIterator : ISimpleIterator<IFrame> {
        override fun forEach(action: (IFrame) -> Unit) =
                mShapes.forEach { it.frame.apply(action) }
    }

    private inner class StylesIterator : ISimpleIterator<IStyle> {
        override fun forEach(action: (IStyle) -> Unit) =
                mShapes.forEach { it.style.apply(action) }
    }
}
