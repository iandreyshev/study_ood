package ru.iandreyshev.adobeKiller.domain.presentationModel

import android.graphics.Bitmap
import ru.iandreyshev.adobeKiller.domain.command.Command
import ru.iandreyshev.adobeKiller.domain.command.ICommandQueue
import ru.iandreyshev.adobeKiller.domain.command.cmd.*
import ru.iandreyshev.adobeKiller.domain.extension.toModel
import ru.iandreyshev.adobeKiller.domain.model.CanvasObject
import ru.iandreyshev.adobeKiller.domain.model.ShapeData
import ru.iandreyshev.adobeKiller.domain.model.ShapeType
import ru.iandreyshev.adobeKiller.presentation.drawing.container.Vec2f
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.IFrame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.IStyle
import ru.iandreyshev.adobeKiller.presentation.drawing.style.Style
import ru.iandreyshev.localstorage.entity.IImageDTO
import ru.iandreyshev.localstorage.entity.IShapeDTO

class PresentationModel(
        private val commandQueue: ICommandQueue
) : IPresentationModel {

    private val mObjects = mutableListOf<CanvasObject>()
    private var mOnObserver: (() -> Unit)? = {}

    override val data: List<CanvasObject>
        get() = mObjects

    override fun fill(shapeDTO: IShapeDTO) {
        val objectModel = CanvasObjectModel()
        val shape = shapeDTO.toModel(objectModel)
        mObjects.add(shape)
    }

    override fun fill(imageDTO: IImageDTO) {
        val objectModel = CanvasObjectModel()
        val shape = imageDTO.toModel(objectModel)
        mObjects.add(shape)
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

    override fun delete(canvasObject: CanvasObject) = observableAction {
        applyCommand {
            RemoveObjectCommand(
                    canvasObject = canvasObject,
                    objectsList = mObjects
            )
        }
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

    override fun clear() = observableAction {
        mObjects.clear()
        commandQueue.clear()
    }

    private fun <T> observableAction(action: () -> T): T {
        val result = action()
        mOnObserver?.invoke()
        return result
    }

    private fun applyCommand(buildCmdAction: () -> Command) =
            observableAction {
                commandQueue.apply(buildCmdAction())
            }

    private inner class CanvasObjectModel : ICanvasObjectModel {

        override fun notifyDataChanges(canvasObject: CanvasObject, prevFrame: IFrame) {
            val newFrame = canvasObject.frame
            val oldPos = prevFrame.position
            val newPos = newFrame.position

            val isSizeChanged = (prevFrame.width != newFrame.width || prevFrame.height != newFrame.height)
            val isPositionChanged = (oldPos.x != newPos.x || oldPos.y != newPos.y)

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

        override fun notifyDataChanges(canvasObject: CanvasObject, prevStyle: IStyle) {
            val newStyle = canvasObject.style

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

            canvasObject.resetProperties()
        }

    }

}
