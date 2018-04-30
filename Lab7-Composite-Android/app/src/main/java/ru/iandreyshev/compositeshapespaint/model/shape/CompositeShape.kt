package ru.iandreyshev.compositeshapespaint.model.shape

import ru.iandreyshev.compositeshapespaint.model.canvas.Color
import ru.iandreyshev.compositeshapespaint.model.canvas.ICanvas
import ru.iandreyshev.compositeshapespaint.model.frame.CompositeFrame
import ru.iandreyshev.compositeshapespaint.model.frame.IFrame
import ru.iandreyshev.compositeshapespaint.model.extension.forEach2
import ru.iandreyshev.compositeshapespaint.model.extension.getAllSameOrNull

class CompositeShape(
        override val name: String,
        shapes: List<IShape>
) : ICompositeShape, CompositeFrame.InnerFramesIterator {

    constructor(name: String, vararg shape: IShape) : this(name, listOf(*shape))

    private val mShapes: MutableList<IShape> = mutableListOf()

    init {
        shapes.forEach { mShapes.add(it) }
    }

    override val composite: ICompositeShape? = this

    override val frame: IFrame =
            CompositeFrame(frames = this)

    override fun getFillColor(): Color? =
            mShapes.getAllSameOrNull { getFillColor() }

    override fun setFillColor(color: Color) =
            mShapes.forEach2 { setFillColor(color) }

    override fun getStrokeColor(): Color? =
            mShapes.getAllSameOrNull { getStrokeColor() }

    override fun setStrokeColor(color: Color) =
            mShapes.forEach2 { setStrokeColor(color) }

    override fun getStrokeSize(): Float? =
            mShapes.getAllSameOrNull { getStrokeSize() }

    override fun setStrokeSize(size: Float) =
            mShapes.forEach2 { setStrokeSize(size) }

    override fun add(shape: IShape) {
        mShapes.add(shape)
    }

    override fun remove(shape: IShape) {
        mShapes.remove(shape)
    }

    override fun draw(canvas: ICanvas) = mShapes.forEach2 { draw(canvas) }

    override fun forEach(action: IFrame.() -> Unit) = mShapes.forEach2 { action(frame) }
}
