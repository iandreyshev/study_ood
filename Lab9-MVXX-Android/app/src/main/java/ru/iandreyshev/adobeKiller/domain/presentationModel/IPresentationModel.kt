package ru.iandreyshev.adobeKiller.domain.presentationModel

import ru.iandreyshev.adobeKiller.domain.file.IFile
import ru.iandreyshev.adobeKiller.domain.model.CanvasObject
import ru.iandreyshev.adobeKiller.domain.model.ShapeType
import ru.iandreyshev.localstorage.entity.IImageDTO
import ru.iandreyshev.localstorage.entity.IShapeDTO

interface IPresentationModel {

    val sceneData: List<CanvasObject>

    fun observeChanges(observer: (() -> Unit)?)

    fun fill(shapeDTO: IShapeDTO)
    fun fill(imageDTO: IImageDTO)

    fun insert(type: ShapeType)
    fun insert(imageFile: IFile)
    fun delete(canvasObject: CanvasObject)

    fun undo()
    fun redo()
    fun clear()

}
