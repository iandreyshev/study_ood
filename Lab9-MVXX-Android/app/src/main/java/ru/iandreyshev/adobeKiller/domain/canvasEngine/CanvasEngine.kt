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

    inner class Factory {
        fun createShape(shapeType: ShapeType): CanvasObject =
                CanvasShape(
                        frame = Frame(),
                        style = Style(),
                        type = shapeType
                ).also { shape ->
                    shape.subscribeOnChange { frame, style ->
                        update(shape, frame)
                        update(shape, style)
                    }
                }

        fun createImage(file: IFile): CanvasObject =
                CanvasImage(
                        frame = Frame(),
                        imageFile = file
                ).also { image ->
                    image.subscribeOnChange { frame, style ->
                        update(image, frame)
                        update(image, style)
                    }
                }
    }

    private val mFactory = Factory()
    private val mSceneObjects = mutableListOf<CanvasObject>()
    private var mChangesObserver: ((List<CanvasObject>) -> Unit)? = {}

    override fun insert(type: ShapeType) {
        commandQueue apply InsertShapeCommand(
                objectsList = mSceneObjects,
                shape = mFactory.createShape(type)
        )
    }

    override fun insert(imageFile: IFile) {
        commandQueue apply InsertImageCommand(
                objectsList = mSceneObjects,
                file = imageFile,
                image = mFactory.createImage(imageFile)
        )
    }

    override fun delete(canvasObject: CanvasObject) {
        commandQueue apply DeleteObjectCommand(
                canvasObject = canvasObject,
                objectsList = mSceneObjects
        )
    }

    override fun observeChanges(observer: ((List<CanvasObject>) -> Unit)?) {
        mChangesObserver = observer
    }

    override fun clear() {
        mSceneObjects.clear()
        commandQueue.clear()
        mChangesObserver?.invoke(mSceneObjects)
    }

    override fun serialize(serializer: ICanvasSerializer) {
        serializer.serialize(mSceneObjects)
    }

    override fun deserialize(serializer: ICanvasSerializer) {
        serializer.deserialize().forEach {
            mSceneObjects.add(it)
        }
    }

    private fun update(canvasObject: CanvasObject, newFrame: IConstFrame) {
        val prevFrame = canvasObject.frame
        val oldPos = canvasObject.frame.position

        val isSizeChanged = (prevFrame.width != newFrame.width || prevFrame.height != newFrame.height)
        val isPositionChanged = (oldPos.x != newFrame.x || oldPos.y != newFrame.y)

        if (isSizeChanged) {
            commandQueue apply ResizeFrameCommand(
                    frame = canvasObject.frame,
                    oldSize = Vec2f(prevFrame.width, prevFrame.height),
                    newSize = Vec2f(newFrame.width, newFrame.height)
            )
        }

        if (isPositionChanged) {
            commandQueue apply MoveFrameCommand(
                    frame = canvasObject.frame,
                    oldPosition = oldPos,
                    newPosition = canvasObject.frame.position
            )
        }
    }

    private fun update(canvasObject: CanvasObject, newStyle: IConstStyle) {
        val prevStyle = canvasObject.style

        if (newStyle.fillColor != prevStyle.fillColor) {
            commandQueue apply ChangeFillColorCommand(
                    style = canvasObject.style,
                    oldColor = prevStyle.fillColor,
                    newColor = newStyle.fillColor)
        }
        if (newStyle.strokeColor != prevStyle.strokeColor) {
            commandQueue apply ChangeStrokeColorCommand(
                    style = canvasObject.style,
                    oldColor = prevStyle.strokeColor,
                    newColor = newStyle.strokeColor
            )
        }

        if (newStyle.strokeSize != prevStyle.strokeSize) {
            commandQueue apply ResizeStrokeCommand(
                    style = canvasObject.style,
                    oldSize = prevStyle.strokeSize,
                    newSize = newStyle.strokeSize
            )
        }
    }

    private infix fun ICommandQueue.apply(command: Command) {
        apply(command)
        mChangesObserver?.invoke(mSceneObjects)
    }

}
