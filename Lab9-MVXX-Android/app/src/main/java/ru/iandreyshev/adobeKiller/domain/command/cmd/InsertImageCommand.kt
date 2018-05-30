package ru.iandreyshev.adobeKiller.domain.command.cmd

import org.jetbrains.anko.doAsync
import ru.iandreyshev.adobeKiller.domain.command.Command
import ru.iandreyshev.adobeKiller.domain.file.IFile
import ru.iandreyshev.adobeKiller.domain.canvasEngine.CanvasObject

class InsertImageCommand(
        private val objectsList: MutableList<CanvasObject>,
        private val file: IFile,
        private val image: CanvasObject
) : Command() {

    private val mPosition: Int = objectsList.size

    override fun onExecute() {
        objectsList.add(image)
        image.onAddedToScene()
    }

    override fun onUndo() {
        objectsList.removeAt(mPosition)
        image.onRemovedFromScene()
    }

    override fun onDestroyNotExecuted() {
        doAsync { file.delete() }
    }

}
