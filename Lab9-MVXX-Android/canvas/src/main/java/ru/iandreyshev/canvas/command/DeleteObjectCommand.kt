package ru.iandreyshev.canvas.command

import ru.iandreyshev.command.Command
import ru.iandreyshev.canvas.core.CanvasImage
import ru.iandreyshev.canvas.core.CanvasObject
import ru.iandreyshev.canvas.core.CanvasShape
import ru.iandreyshev.canvas.core.ICanvasObjectVisitor

internal class DeleteObjectCommand(
        private val canvasObject: CanvasObject,
        private val objectsList: MutableList<CanvasObject>
) : Command() {

    private val mObjectPosition: Int = objectsList.indexOf(canvasObject)
    private val mClearVisitor = ClearObjectDataVisitor()

    override fun onExecute() {
        objectsList.removeAt(mObjectPosition)
        canvasObject.onRemovedFromScene()
    }

    override fun onUndo() {
        objectsList.add(mObjectPosition, canvasObject)
        canvasObject.onAddedToScene()
    }

    override fun onDestroyExecuted() {
        canvasObject.accept(mClearVisitor)
    }

    private class ClearObjectDataVisitor : ICanvasObjectVisitor {

        override fun visit(image: CanvasImage) {
            image.imageFile.delete()
        }

        override fun visit(shape: CanvasShape) = Unit

    }

}
