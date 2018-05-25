package ru.iandreyshev.adobeKiller.domain.presentationModel

import android.graphics.Bitmap
import ru.iandreyshev.adobeKiller.domain.model.CanvasObjectData
import ru.iandreyshev.adobeKiller.domain.model.ShapeType
import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.Color

interface IPresentationModel {

    val data: List<CanvasObjectData>

    fun fill(objectData: CanvasObjectData)
    fun insert(shape: ShapeType): CanvasObjectData
    fun insert(image: Bitmap): CanvasObjectData
    fun delete(id: Long)

    fun resize(id: Long, width: Float, height: Float)
    fun move(id: Long, x: Float, y: Float)

    fun changeFillColor(id: Long, color: Color)
    fun changeStrokeColor(id: Long, color: Color)
    fun resizeStroke(id: Long, size: Int)

    fun observe(observer: (() -> Unit)?)

    fun undo()
    fun redo()

    fun reset()

}
