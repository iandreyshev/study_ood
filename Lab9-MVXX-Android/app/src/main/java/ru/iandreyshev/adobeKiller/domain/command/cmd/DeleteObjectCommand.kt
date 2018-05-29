package ru.iandreyshev.adobeKiller.domain.command.cmd

import org.jetbrains.anko.doAsync
import ru.iandreyshev.adobeKiller.domain.command.Command
import ru.iandreyshev.adobeKiller.domain.model.CanvasObject
import ru.iandreyshev.adobeKiller.domain.model.ICanvasObjectVisitor
import ru.iandreyshev.adobeKiller.domain.model.CanvasImage
import ru.iandreyshev.adobeKiller.domain.model.CanvasShape

class DeleteObjectCommand(
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
            doAsync { image.imageFile.delete() }
        }

        override fun visit(shape: CanvasShape) = Unit

    }

}
