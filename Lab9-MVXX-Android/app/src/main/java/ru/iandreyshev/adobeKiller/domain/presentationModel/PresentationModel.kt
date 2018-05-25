package ru.iandreyshev.adobeKiller.domain.presentationModel

import android.graphics.Bitmap
import ru.iandreyshev.adobeKiller.domain.command.ICommandQueue
import ru.iandreyshev.adobeKiller.domain.command.cmd.ChangeFillColorCommand
import ru.iandreyshev.adobeKiller.domain.command.cmd.ChangeStrokeColorCommand
import ru.iandreyshev.adobeKiller.domain.command.cmd.ResizeStrokeCommand
import ru.iandreyshev.adobeKiller.domain.model.CanvasObjectData
import ru.iandreyshev.adobeKiller.domain.model.ShapeData
import ru.iandreyshev.adobeKiller.domain.model.ShapeType
import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.Color
import ru.iandreyshev.adobeKiller.presentation.drawing.container.Vec2f
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.Style

class PresentationModel(
        private val commandQueue: ICommandQueue
) : IPresentationModel {

    private val mObjects = mutableListOf<CanvasObjectData>()
    private var mOnObserver: (() -> Unit)? = {}

    override val data: List<CanvasObjectData>
        get() = mObjects

    override fun fill(objectData: CanvasObjectData) = observableAction<Unit> {
        mObjects.add(objectData)
    }

    override fun insert(shape: ShapeType): CanvasObjectData = observableAction {
        val newObject = ShapeData(
                type = shape,
                frame = Frame(Vec2f(0, 0), 100f, 100f),
                style = Style()
        )

        mObjects.add(newObject)
        return@observableAction newObject
    }

    override fun insert(image: Bitmap): CanvasObjectData = observableAction {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun resize(id: Long, width: Float, height: Float) {
        mObjects.find { it.id == id }?.apply {
            frame.resize(width, height)
        }
    }

    override fun move(id: Long, x: Float, y: Float) {
        mObjects.find { it.id == id }?.apply {
            frame.position.x = x
            frame.position.y = y
        }
    }

    override fun changeFillColor(id: Long, color: Color) {
        val item = getOrNull(id) ?: return
        val command = ChangeFillColorCommand(item.style, color)

        observableAction {
            commandQueue.apply(command)
        }
    }

    override fun changeStrokeColor(id: Long, color: Color) {
        val item = getOrNull(id) ?: return
        val command = ChangeStrokeColorCommand(item.style, color)

        observableAction {
            commandQueue.apply(command)
        }
    }

    override fun resizeStroke(id: Long, size: Int) {
        val item = getOrNull(id) ?: return
        val command = ResizeStrokeCommand(item.style, size.toFloat())

        observableAction {
            commandQueue.apply(command)
        }
    }

    override fun observe(observer: (() -> Unit)?) {
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

    private fun getOrNull(id: Long): CanvasObjectData? =
            mObjects.find { it.id == id }

}
