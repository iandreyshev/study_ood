package ru.iandreyshev.adobeKiller.domain.presentationModel

import ru.iandreyshev.adobeKiller.domain.command.Command
import ru.iandreyshev.adobeKiller.domain.command.ICommandQueue
import ru.iandreyshev.adobeKiller.domain.command.cmd.*
import ru.iandreyshev.adobeKiller.domain.extension.toModel
import ru.iandreyshev.adobeKiller.domain.file.IFile
import ru.iandreyshev.adobeKiller.domain.model.CanvasObject
import ru.iandreyshev.adobeKiller.domain.model.CanvasImage
import ru.iandreyshev.adobeKiller.domain.model.CanvasShape
import ru.iandreyshev.adobeKiller.domain.model.ShapeType
import ru.iandreyshev.adobeKiller.presentation.drawing.container.Vec2f
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.IConstFrame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.IConstStyle
import ru.iandreyshev.adobeKiller.presentation.drawing.style.Style
import ru.iandreyshev.localstorage.entity.IImageDTO
import ru.iandreyshev.localstorage.entity.IShapeDTO

class PresentationModel(
        private val commandQueue: ICommandQueue
) : IPresentationModel {

    inner class Factory {
        fun createShape(shapeType: ShapeType): CanvasObject =
                CanvasShape(
                        frame = Frame(),
                        style = Style(),
                        type = shapeType
                )

        fun createImage(file: IFile): CanvasObject =
                CanvasImage(
                        frame = Frame(),
                        imageFile = file
                )
    }

    private val mFactory = Factory()
    private val mSceneObjects = mutableListOf<CanvasObject>()
    private var mChangesObserver: (() -> Unit)? = {}

    fun fill(shapeDTO: IShapeDTO) {
        val shape = shapeDTO.toModel()
        mSceneObjects.add(shape)
    }

    fun fill(imageDTO: IImageDTO) {
        val shape = imageDTO.toModel()
        mSceneObjects.add(shape)
    }

    override fun insert(type: ShapeType) = observableAction {
        applyCommand {
            InsertShapeCommand(
                    objectsList = mSceneObjects,
                    shapeType = type,
                    shapesFactory = mFactory::createShape
            )
        }
    }

    override fun insert(imageFile: IFile) = observableAction {
        applyCommand {
            InsertImageCommand(
                    objectsList = mSceneObjects,
                    file = imageFile,
                    imagesFactory = mFactory::createImage
            )
        }
    }

    override fun update(canvasObject: CanvasObject, newFrame: IConstFrame) {
        val prevFrame = canvasObject.frame
        val oldPos = canvasObject.frame.position

        val isSizeChanged = (prevFrame.width != newFrame.width || prevFrame.height != newFrame.height)
        val isPositionChanged = (oldPos.x != newFrame.x || oldPos.y != newFrame.y)

        if (isSizeChanged) applyCommand {
            ResizeFrameCommand(
                    frame = canvasObject.frame,
                    oldSize = Vec2f(prevFrame.width, prevFrame.height),
                    newSize = Vec2f(newFrame.width, newFrame.height)
            )
        }

        if (isPositionChanged) applyCommand {
            MoveFrameCommand(
                    frame = canvasObject.frame,
                    oldPosition = oldPos,
                    newPosition = canvasObject.frame.position
            )
        }
    }

    override fun update(canvasObject: CanvasObject, newStyle: IConstStyle) {
        val prevStyle = canvasObject.style

        if (newStyle.fillColor != prevStyle.fillColor) applyCommand {
            ChangeFillColorCommand(
                    style = canvasObject.style,
                    oldColor = prevStyle.fillColor,
                    newColor = newStyle.fillColor)
        }
        if (newStyle.strokeColor != prevStyle.strokeColor) applyCommand {
            ChangeStrokeColorCommand(
                    style = canvasObject.style,
                    oldColor = prevStyle.strokeColor,
                    newColor = newStyle.strokeColor
            )
        }

        if (newStyle.strokeSize != prevStyle.strokeSize) applyCommand {
            ResizeStrokeCommand(
                    style = canvasObject.style,
                    oldSize = prevStyle.strokeSize,
                    newSize = newStyle.strokeSize
            )
        }
    }

    override fun delete(canvasObject: CanvasObject) = observableAction {
        applyCommand {
            DeleteObjectCommand(
                    canvasObject = canvasObject,
                    objectsList = mSceneObjects
            )
        }
    }

    override fun observeChanges(observer: (() -> Unit)?) {
        mChangesObserver = observer
    }

    override fun clear() = observableAction {
        mSceneObjects.clear()
        commandQueue.clear()
    }

    override fun serialize(serializer: ICanvasSerializer) {
        serializer.serialize(mSceneObjects)
    }

    private fun <T> observableAction(action: () -> T): T {
        val result = action()
        mChangesObserver?.invoke()
        return result
    }

    private fun applyCommand(buildCmdAction: () -> Command) =
            observableAction { commandQueue.apply(buildCmdAction) }

}
