package ru.iandreyshev.adobeKiller.domain.command.cmd

import ru.iandreyshev.adobeKiller.domain.command.Command
import ru.iandreyshev.adobeKiller.domain.canvasEngine.CanvasObject

class InsertShapeCommand(
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
