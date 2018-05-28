package ru.iandreyshev.adobeKiller.domain.command.cmd

import ru.iandreyshev.adobeKiller.domain.command.Command
import ru.iandreyshev.adobeKiller.domain.model.CanvasObject
import ru.iandreyshev.adobeKiller.domain.model.ShapeType

class InsertShapeCommand(
        private val objectsList: MutableList<CanvasObject>,
        shapeType: ShapeType,
        shapesFactory: (ShapeType) -> CanvasObject
) : Command() {

    private val mPosition: Int = objectsList.size
    private val mShape: CanvasObject = shapesFactory(shapeType)

    override fun onExecute() {
        objectsList.add(mShape)
        mShape.onAddedToScene()
    }

    override fun onUndo() {
        objectsList.removeAt(mPosition)
        mShape.onRemovedFromScene()
    }

}
