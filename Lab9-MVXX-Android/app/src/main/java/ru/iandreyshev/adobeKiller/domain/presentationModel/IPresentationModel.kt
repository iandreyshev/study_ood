package ru.iandreyshev.adobeKiller.domain.presentationModel

import android.graphics.Bitmap
import ru.iandreyshev.adobeKiller.domain.model.CanvasObject
import ru.iandreyshev.adobeKiller.domain.model.ShapeType
import ru.iandreyshev.localstorage.entity.IImageDTO
import ru.iandreyshev.localstorage.entity.IShapeDTO

interface IPresentationModel {

    val data: List<CanvasObject>

    fun observeChanges(observer: (() -> Unit)?)

    fun fill(shapeDTO: IShapeDTO)
    fun fill(imageDTO: IImageDTO)

    fun insert(shape: ShapeType): CanvasObject
    fun insert(image: Bitmap): CanvasObject
    fun delete(canvasObject: CanvasObject)

    fun undo()
    fun redo()
    fun clear()

}
