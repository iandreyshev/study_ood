package ru.iandreyshev.localstorage

import ru.iandreyshev.localstorage.entity.ICanvasDTO
import ru.iandreyshev.localstorage.entity.IImageDTO
import ru.iandreyshev.localstorage.entity.IShapeDTO

interface ILocalStorage {

    fun createCanvas(name: String): ICanvasDTO

    fun getCanvases(): List<ICanvasDTO>
    fun getShapes(canvasId: Long): List<IShapeDTO>
    fun getImages(canvasId: Long): List<IImageDTO>

    fun saveShapes(canvasId: Long, shapes: List<IShapeDTO>)
    fun saveImages(canvasId: Long, images: List<IImageDTO>)

    fun clearCanvas(canvasId: Long)
    fun deleteCanvas(canvasId: Long)

}
