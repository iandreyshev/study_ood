package ru.iandreyshev.canvas.core

import ru.iandreyshev.canvas.command.*
import ru.iandreyshev.command.Command
import ru.iandreyshev.command.ICommandQueue
import ru.iandreyshev.canvas.file.IFile
import ru.iandreyshev.canvas.presenter.ICanvasPresenter
import ru.iandreyshev.canvas.storage.ICanvasStorage
import ru.iandreyshev.canvas.style.IConstStyle
import ru.iandreyshev.canvas.style.Style
import ru.iandreyshev.geometry.frame.Frame
import ru.iandreyshev.geometry.frame.IConstFrame
import ru.iandreyshev.geometry.vector.Vec2f

class Canvas(
        private val commandQueue: ICommandQueue,
        private val serializer: ICanvasStorage
) : ICanvas {

    private var mPresenter: ICanvasPresenter? = null
    private val mFactory = Factory()
    private val mSceneObjects = mutableListOf<CanvasObject>()
    private var mUpdatesObserver: ((List<CanvasObject>?) -> Unit)? = {}

    override fun setPresenter(presenter: ICanvasPresenter) {
        mPresenter = presenter
    }

    override fun insert(type: ShapeType) {
        commandQueue apply InsertShapeCommand(
                objectsList = mSceneObjects,
                shape = mFactory.createShape(type)
        )
        invalidateWithRecreate()
    }

    override fun insert(imageFile: IFile) {
        commandQueue apply InsertImageCommand(
                objectsList = mSceneObjects,
                file = imageFile,
                image = mFactory.createImage(imageFile)
        )
        invalidateWithRecreate()
    }

    override fun clear() {
        mSceneObjects.clear()
        commandQueue.clear()
        invalidateWithRecreate()
    }

    override fun load() {
        mSceneObjects.clear()
        mSceneObjects.addAll(serializer.deserialize())
    }

    override fun save() {
        serializer.serialize(mSceneObjects)
    }

    private fun delete(canvasObject: CanvasObject) {
        commandQueue apply DeleteObjectCommand(
                canvasObject = canvasObject,
                objectsList = mSceneObjects
        )
        invalidateWithRecreate()
    }

    private fun update(objectFrame: Frame, newFrame: IConstFrame) {
        val oldPos = objectFrame.position
        val isSizeChanged = (objectFrame.width != newFrame.width || objectFrame.height != newFrame.height)
        val isPositionChanged = (oldPos.x != newFrame.x || oldPos.y != newFrame.y)

        if (isSizeChanged) {
            commandQueue apply ResizeFrameCommand(
                    frame = objectFrame,
                    oldSize = Vec2f(objectFrame.width, objectFrame.height),
                    newSize = Vec2f(newFrame.width, newFrame.height)
            )
        }

        if (isPositionChanged) {
            commandQueue apply MoveFrameCommand(
                    frame = objectFrame,
                    oldPosition = oldPos,
                    newPosition = Vec2f(newFrame.x, newFrame.y)
            )
        }

        invalidate()
    }

    private fun update(objectStyle: Style, newStyle: IConstStyle) {
        if (newStyle.fillColor != objectStyle.fillColor) {
            commandQueue apply ChangeFillColorCommand(
                    style = objectStyle,
                    oldColor = objectStyle.fillColor,
                    newColor = newStyle.fillColor)
        }
        if (newStyle.strokeColor != objectStyle.strokeColor) {
            commandQueue apply ChangeStrokeColorCommand(
                    style = objectStyle,
                    oldColor = objectStyle.strokeColor,
                    newColor = newStyle.strokeColor
            )
        }

        if (newStyle.strokeSize != objectStyle.strokeSize) {
            commandQueue apply ResizeStrokeCommand(
                    style = objectStyle,
                    oldSize = objectStyle.strokeSize,
                    newSize = newStyle.strokeSize
            )
        }

        invalidate()
    }

    private infix fun ICommandQueue.apply(command: Command) {
        apply(command)
    }

    private fun invalidate() {
        mUpdatesObserver?.invoke(null)
    }

    private fun invalidateWithRecreate() {
        mUpdatesObserver?.invoke(mSceneObjects)
    }

    inner class Factory {
        fun createShape(shapeType: ShapeType): CanvasObject =
                TODO()

        fun createImage(file: IFile): CanvasObject =
                TODO()
    }

}
