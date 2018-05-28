package ru.iandreyshev.adobeKiller.domain.command.cmd

import org.jetbrains.anko.doAsync
import ru.iandreyshev.adobeKiller.domain.command.Command
import ru.iandreyshev.adobeKiller.domain.file.IFile
import ru.iandreyshev.adobeKiller.domain.model.CanvasObject

class InsertImageCommand(
        private val objectsList: MutableList<CanvasObject>,
        private val file: IFile,
        imagesFactory: (IFile) -> CanvasObject
) : Command() {

    private val mPosition: Int = objectsList.size
    private val mImage: CanvasObject = imagesFactory(file)

    override fun onExecute() {
        objectsList.add(mImage)
        mImage.onAddedToScene()
    }

    override fun onUndo() {
        objectsList.removeAt(mPosition)
        mImage.onRemovedFromScene()
    }

    override fun onDestroyNotExecuted() {
        doAsync { file.delete() }
    }

}
