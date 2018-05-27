package ru.iandreyshev.adobeKiller.domain.presentationModel

import android.graphics.Bitmap
import ru.iandreyshev.adobeKiller.domain.command.ICommandQueue
import ru.iandreyshev.adobeKiller.domain.model.CanvasObject
import ru.iandreyshev.adobeKiller.domain.model.ShapeData
import ru.iandreyshev.adobeKiller.domain.model.ShapeType
import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.Color
import ru.iandreyshev.adobeKiller.presentation.drawing.container.Vec2f
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.IFrame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.IStyle
import ru.iandreyshev.adobeKiller.presentation.drawing.style.Style

class PresentationModel(
        private val commandQueue: ICommandQueue
) : IPresentationModel {

    private val mObjects = mutableListOf<CanvasObject>()
    private var mOnObserver: (() -> Unit)? = {}

    override val data: List<CanvasObject>
        get() = mObjects

    fun fill(objectData: CanvasObject) = observableAction<Unit> {
        mObjects.add(objectData)
    }

    override fun insert(shape: ShapeType): CanvasObject = observableAction {
        val newObject = ShapeData(
                frame = Frame(Vec2f(0, 0), 100f, 100f),
                style = Style(),
                model = CanvasObjectModel(),
                type = shape
        )

        mObjects.add(newObject)
        return@observableAction newObject
    }

    override fun insert(image: Bitmap): CanvasObject = observableAction {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(canvasObject: CanvasObject) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun resize(id: Long, width: Float, height: Float) {
    }

    fun move(id: Long, x: Float, y: Float) {
    }

    fun changeFillColor(id: Long, color: Color) {
    }

    fun changeStrokeColor(id: Long, color: Color) {
    }

    fun resizeStroke(id: Long, size: Int) {
    }

    override fun observeChanges(observer: (() -> Unit)?) {
        mOnObserver = observer
    }

    override fun undo() {
        if (commandQueue.canUndo) {
            observableAction { commandQueue.undo() }
        }
    }

    override fun redo() {
        if (commandQueue.canRedo) {
            observableAction { commandQueue.redo() }
        }
    }

    override fun reset() {
        mObjects.clear()
        commandQueue.clear()
    }

    private fun <T> observableAction(action: () -> T): T {
        val result = action()
        mOnObserver?.invoke()
        return result
    }

    private inner class CanvasObjectModel : ICanvasObjectModel {

        override fun notifyDataChanges(canvasObject: CanvasObject, newFrame: IFrame) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun notifyDataChanges(canvasObject: CanvasObject, newStyle: IStyle) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

}
