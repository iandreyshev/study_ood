package ru.iandreyshev.localstorage

import ru.iandreyshev.localstorage.entity.ICanvasEntity
import ru.iandreyshev.localstorage.entity.IImageEntity
import ru.iandreyshev.localstorage.entity.IShapeEntity

interface ILocalStorage {

    fun createCanvas(name: String): ICanvasEntity

    fun getCanvases(): List<ICanvasEntity>

    fun getShapes(canvasId: Long): List<IShapeEntity>

    fun getImages(canvasId: Long): List<IImageEntity>

    fun saveShapes(canvasId: Long, shapes: List<IShapeEntity>)

    fun saveImages(canvasId: Long, images: List<IImageEntity>)

    fun deleteCanvas(canvasId: Long)

}
