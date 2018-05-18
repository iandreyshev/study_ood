package ru.iandreyshev.adobeKiller.data

import io.objectbox.BoxStore

class Repository(localStorage: BoxStore) : IRepository {

    private val mCanvasesBox = localStorage.boxFor(DbCanvas::class.java)
    private val mShapesBox = localStorage.boxFor(DbShape::class.java)

    override fun createCanvas(name: String): DbCanvas {
        val entity = DbCanvas(
                name = name
        )

        mCanvasesBox.put(entity)

        return entity
    }

    override fun getCanvases(): List<DbCanvas> =
            mCanvasesBox.all

    override fun getShapesForCanvas(canvasId: Long): List<DbShape> =
            mShapesBox.find(DbShape_.canvasId, canvasId)

}
