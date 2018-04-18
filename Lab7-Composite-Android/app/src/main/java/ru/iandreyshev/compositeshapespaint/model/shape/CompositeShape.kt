package shape

import canvas.Color
import canvas.ICanvas
import containers.CompositeFrame
import containers.AbstractFrame
import extension.forEach2
import extension.getAllSameOrNull

class CompositeShape : ICompositeShape, CompositeFrame.InnerFramesIterator {
    private val mShapes: HashSet<IShape> = HashSet()

    override val composite: ICompositeShape? = this

    override val frame: AbstractFrame = CompositeFrame(frames = this)

    override fun getFillColor(): Color? =
            mShapes.getAllSameOrNull { getFillColor() }

    override fun setFillColor(color: Color) =
            mShapes.forEach2 { setFillColor(color) }

    override fun getStroleColor(): Color? =
            mShapes.getAllSameOrNull { getStroleColor() }

    override fun setStroleColor(color: Color) =
            mShapes.forEach2 { setStroleColor(color) }

    override fun getStrokeSize(): Int? =
            mShapes.getAllSameOrNull { getStrokeSize() }

    override fun setStrokeSize(size: Int) =
            mShapes.forEach2 { setStrokeSize(size) }

    override fun add(shape: IShape) {
        mShapes.add(shape)
    }

    override fun remove(shape: IShape) {
        mShapes.remove(shape)
    }

    override fun draw(canvas: ICanvas) = mShapes.forEach2 { draw(canvas) }

    override fun forEach(action: AbstractFrame.() -> Unit) = mShapes.forEach2 { action(frame) }
}
