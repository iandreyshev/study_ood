package ru.iandreyshev.adobeKiller.data

interface IRepository {

    fun createCanvas(name: String): DbCanvas

    fun getCanvases(): List<DbCanvas>

    fun getShapesForCanvas(canvasId: Long): List<DbShape>

}
