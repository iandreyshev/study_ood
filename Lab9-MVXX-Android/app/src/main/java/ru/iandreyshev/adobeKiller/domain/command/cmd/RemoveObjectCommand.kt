package ru.iandreyshev.adobeKiller.domain.command.cmd

import ru.iandreyshev.adobeKiller.domain.command.Command
import ru.iandreyshev.adobeKiller.domain.model.CanvasObject

class RemoveObjectCommand(
        private val canvasObject: CanvasObject,
        private val objectsList: MutableList<CanvasObject>
) : Command() {

    private val mObjectPosition: Int = objectsList.indexOf(canvasObject)

    override fun onExecute() {
        objectsList.removeAt(mObjectPosition)
    }

    override fun onUndo() {
        objectsList.add(mObjectPosition, canvasObject)
    }

}
