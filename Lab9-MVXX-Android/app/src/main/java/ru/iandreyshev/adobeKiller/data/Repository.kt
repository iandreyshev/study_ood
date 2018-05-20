package ru.iandreyshev.adobeKiller.data

import io.objectbox.BoxStore

class Repository(localStorage: BoxStore) : IRepository {

    private val mCanvasesBox = localStorage.boxFor(DbCanvas::class.java)
    private val mShapesBox = localStorage.boxFor(DbShape::class.java)

    override fun createCanvas(name: String): DbCanvas =
            DbCanvas(name = name).apply {
                mCanvasesBox.put(this)
            }

    override fun getCanvases(): List<DbCanvas> =
            mCanvasesBox.all

    override fun getCanvasShapes(canvasId: Long): List<DbShape> =
            mShapesBox.find(DbShape_.canvasId, canvasId)

    override fun saveCanvas(canvasId: Long, shapes: List<DbShape>) {
        mShapesBox.query()
                .equal(DbShape_.canvasId, canvasId)
                .build()
                .remove()
        canvasId.bindShapes(shapes)
        mShapesBox.put(shapes)
    }

    override fun deleteCanvas(canvasId: Long) {
        mCanvasesBox.remove(canvasId)
        mShapesBox.query()
                .equal(DbShape_.canvasId, canvasId)
                .build()
                .remove()
    }

    private fun Long.bindShapes(shapes: List<DbShape>) {
        shapes.forEach { it.canvasId = this }
    }

}
