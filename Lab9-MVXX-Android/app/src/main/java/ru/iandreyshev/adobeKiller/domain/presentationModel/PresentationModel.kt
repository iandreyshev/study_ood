package ru.iandreyshev.adobeKiller.domain.presentationModel

import ru.iandreyshev.adobeKiller.domain.command.Command
import ru.iandreyshev.adobeKiller.domain.command.ICommandQueue
import ru.iandreyshev.adobeKiller.domain.command.cmd.*
import ru.iandreyshev.adobeKiller.domain.extension.toModel
import ru.iandreyshev.adobeKiller.domain.file.IFile
import ru.iandreyshev.adobeKiller.domain.model.CanvasObject
import ru.iandreyshev.adobeKiller.domain.model.ImageObject
import ru.iandreyshev.adobeKiller.domain.model.ShapeObject
import ru.iandreyshev.adobeKiller.domain.model.ShapeType
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.Style
import ru.iandreyshev.localstorage.entity.IImageDTO
import ru.iandreyshev.localstorage.entity.IShapeDTO

class PresentationModel(
        private val commandQueue: ICommandQueue
) : IPresentationModel {

    inner class Factory {
        fun createShape(shapeType: ShapeType): CanvasObject =
                ShapeObject(
                        frame = Frame(),
                        style = Style(),
                        model = newCanvasObjectModel(),
                        type = shapeType
                )

        fun createImage(file: IFile): CanvasObject =
                ImageObject(
                        frame = Frame(),
                        model = newCanvasObjectModel(),
                        imageFile = file
                )
    }

    private val mFactory = Factory()
    private val mSceneObjects = mutableListOf<CanvasObject>()
    private var mChangesObserver: (() -> Unit)? = {}

    override val sceneData: List<CanvasObject>
        get() = mSceneObjects

    override fun fill(shapeDTO: IShapeDTO) {
        val objectModel = newCanvasObjectModel()
        val shape = shapeDTO.toModel(objectModel)
        mSceneObjects.add(shape)
    }

    override fun fill(imageDTO: IImageDTO) {
        val objectModel = newCanvasObjectModel()
        val shape = imageDTO.toModel(objectModel)
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
        mSceneObjects.clear()
        commandQueue.clear()
    }

    private fun <T> observableAction(action: () -> T): T {
        val result = action()
        mChangesObserver?.invoke()
        return result
    }

    private fun applyCommand(buildCmdAction: () -> Command) =
            observableAction { commandQueue.apply(buildCmdAction) }

    private fun newCanvasObjectModel(): ICanvasObjectModel {
        return CanvasObjectModel(commandQueue, mChangesObserver ?: {})
    }

}
