package shape

import canvas.Color
import canvas.ICanvas
import containers.CompositeFrame
import containers.IFrame
import extension.forEach2
import extension.getAllSameOrNull

class CompositeShape : ICompositeShape, CompositeFrame.InnerFramesIterator {
    private val mShapes: HashSet<IShape> = HashSet()

    override val composite: ICompositeShape? = this

    override val frame: IFrame = CompositeFrame(frames = this)

    override var strokeColor: Color?
        get() = mShapes.getAllSameOrNull { strokeColor }
        set(value) {
            value ?: return
            mShapes.forEach2 { strokeColor = value }
        }

    override var strokeSize: Int?
        get() = mShapes.getAllSameOrNull { strokeSize }
        set(value) {
            value ?: return
            mShapes.forEach2 { strokeSize = value }
        }

    override var fillColor: Color?
        get() = mShapes.getAllSameOrNull { fillColor }
        set(value) {
            value ?: return
            mShapes.forEach2 { fillColor = value }
        }

    override fun add(shape: IShape) {
        mShapes.add(shape)
    }

    override fun remove(shape: IShape) {
        mShapes.remove(shape)
    }

    override fun draw(canvas: ICanvas) = mShapes.forEach2 { draw(canvas) }

    override fun forEach(action: IFrame.() -> Unit) = mShapes.forEach2 { action(frame) }
}
