package ru.iandreyshev.adobeKiller.data

interface IRepository {

    fun createCanvas(name: String): DbCanvas

    fun getCanvases(): List<DbCanvas>

    fun getCanvasShapes(canvasId: Long): List<DbShape>

    fun saveCanvas(canvasId: Long, shapes: List<DbShape>)

    fun deleteCanvas(canvasId: Long)

}
