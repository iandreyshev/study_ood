package ru.iandreyshev.adobeKiller.domain.canvasEngine

import ru.iandreyshev.adobeKiller.domain.command.Command
import ru.iandreyshev.adobeKiller.domain.command.ICommandQueue
import ru.iandreyshev.adobeKiller.domain.command.cmd.*
import ru.iandreyshev.adobeKiller.domain.file.IFile
import ru.iandreyshev.adobeKiller.presentation.drawing.container.Vec2f
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.IConstFrame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.IConstStyle
import ru.iandreyshev.adobeKiller.presentation.drawing.style.Style

class CanvasEngine(
        private val commandQueue: ICommandQueue
) : ICanvasEngine {

    private val mFactory = Factory()
    private val mSceneObjects = mutableListOf<CanvasObject>()
    private var mUpdatesObserver: ((List<CanvasObject>?) -> Unit)? = {}

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

    override fun observeUpdate(observer: ((List<CanvasObject>?) -> Unit)?) {
        mUpdatesObserver = observer
        invalidateWithRecreate()
    }

    override fun clear() {
        mSceneObjects.clear()
        commandQueue.clear()
        invalidateWithRecreate()
    }

    override fun serialize(serializer: ICanvasSerializer) {
        serializer.serialize(mSceneObjects)
    }

    override fun deserialize(serializer: ICanvasSerializer) {
        serializer.deserialize().forEach {
            mSceneObjects.add(it)
        }
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
                CanvasShape(
                        frame = Frame(),
                        style = Style(),
                        type = shapeType
                ).apply {
                    setOnUpdateListener { frame, style ->
                        this@CanvasEngine.update(this.frame, frame)
                        this@CanvasEngine.update(this.style, style)
                        this@CanvasEngine.invalidate()
                    }
                    setOnDeleteListener {
                        this@CanvasEngine.delete(this)
                        this@CanvasEngine.invalidateWithRecreate()
                    }
                }

        fun createImage(file: IFile): CanvasObject =
                CanvasImage(
                        frame = Frame(),
                        imageFile = file
                ).apply {
                    setOnUpdateListener { frame, style ->
                        this@CanvasEngine.update(this.frame, frame)
                        this@CanvasEngine.update(this.style, style)
                        this@CanvasEngine.invalidate()
                    }
                    setOnDeleteListener {
                        this@CanvasEngine.delete(this)
                        this@CanvasEngine.invalidateWithRecreate()
                    }
                }
    }

}
