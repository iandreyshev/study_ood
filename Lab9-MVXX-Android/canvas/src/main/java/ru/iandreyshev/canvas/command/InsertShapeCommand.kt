package ru.iandreyshev.canvas.command

import ru.iandreyshev.command.Command
import ru.iandreyshev.canvas.core.CanvasObject

internal class InsertShapeCommand(
        private val objectsList: MutableList<CanvasObject>,
        private val shape: CanvasObject
) : Command() {

    private val mPosition: Int = objectsList.size

    override fun onExecute() {
        objectsList.add(shape)
        shape.onAddedToScene()
    }

    override fun onUndo() {
        objectsList.removeAt(mPosition)
        shape.onRemovedFromScene()
    }

}
