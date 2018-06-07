package ru.iandreyshev.canvas.core

import ru.iandreyshev.canvas.command.*
import ru.iandreyshev.command.ICommandQueue
import ru.iandreyshev.canvas.file.IFile
import ru.iandreyshev.canvas.presenter.ICanvasPresenter
import ru.iandreyshev.canvas.storage.ICanvasStorage
import ru.iandreyshev.canvas.style.IConstStyle
import ru.iandreyshev.canvas.style.Style
import ru.iandreyshev.command.apply
import ru.iandreyshev.geometry.frame.Frame
import ru.iandreyshev.geometry.frame.IConstFrame
import ru.iandreyshev.geometry.vector.Vec2f

class Canvas(
        private val commandQueue: ICommandQueue,
        private val serializer: ICanvasStorage
) : ICanvas {

    private val mFactory = CanvasObjectFactory()
    private var mPresenter: ICanvasPresenter? = null
    private val mSceneObjects = mutableListOf<CanvasObject>()

    override fun setPresenter(presenter: ICanvasPresenter) {
        mPresenter = presenter
    }

    override fun insert(type: ShapeType) {
        commandQueue apply InsertShapeCommand(
                objectsList = mSceneObjects,
                shape = mFactory.createShape(type)
        )
        mPresenter?.update(mSceneObjects)
    }

    override fun insert(imageFile: IFile) {
        commandQueue apply InsertImageCommand(
                objectsList = mSceneObjects,
                file = imageFile,
                image = mFactory.createImage(imageFile)
        )
        mPresenter?.update(mSceneObjects)
    }

    override fun update() {
        mPresenter?.update(mSceneObjects)
    }

    override fun clear() {
        mSceneObjects.clear()
        commandQueue.clear()
        mPresenter?.update(mSceneObjects)
    }

    override fun load() {
        mSceneObjects.clear()
        mSceneObjects.addAll(serializer.deserialize())
        mPresenter?.update(mSceneObjects)
    }

    override fun save() {
        serializer.serialize(mSceneObjects)
    }

    private fun delete(canvasObject: CanvasObject) {
        commandQueue apply DeleteObjectCommand(
                canvasObject = canvasObject,
                objectsList = mSceneObjects
        )
        mPresenter?.update(mSceneObjects)
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

        mPresenter?.update()
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

        mPresenter?.update()
    }

    inner class CanvasObjectFactory : ICanvasObjectFactory {

        private val mFrameProto = Frame(width = 100f, height = 100f)
        private val mStyleProto = Style()

        override fun createShape(shapeType: ShapeType): CanvasObject =
                CanvasShape(
                        frame = mFrameProto.clone(),
                        style = mStyleProto.clone(),
                        type = shapeType
                ).also { shape ->
                    shape.updateFrameListener = { objectFrame, newFrame -> update(objectFrame, newFrame) }
                    shape.updateStyleListener = { objectStyle, newStyle -> update(objectStyle, newStyle) }
                    shape.deleteListener = { objectToDelete -> delete(objectToDelete) }
                }

        override fun createImage(file: IFile): CanvasObject =
                CanvasImage(
                        frame = mFrameProto.clone(),
                        imageFile = file
                ).also { image ->
                    image.updateFrameListener = { objectFrame, newFrame -> update(objectFrame, newFrame) }
                    image.deleteListener = { objectToDelete -> delete(objectToDelete) }
                }

    }


}
