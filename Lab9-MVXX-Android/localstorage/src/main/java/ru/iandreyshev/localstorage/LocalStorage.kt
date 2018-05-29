package ru.iandreyshev.localstorage

import io.objectbox.BoxStore
import ru.iandreyshev.localstorage.entity.*
import ru.iandreyshev.localstorage.extension.entity
import ru.iandreyshev.localstorage.extension.asDTO

class LocalStorage(boxStore: BoxStore) : ILocalStorage {

    private val mCanvases = boxStore.boxFor(CanvasEntity::class.java)
    private val mShapes = boxStore.boxFor(ShapeEntity::class.java)
    private val mImages = boxStore.boxFor(ImageEntity::class.java)

    override fun createCanvas(name: String): ICanvasDTO =
            CanvasEntity(name = name).apply {
                mCanvases.put(this)
            }.asDTO

    override fun getCanvases(): List<ICanvasDTO> =
            mCanvases.all.map { it.asDTO }

    override fun getShapes(canvasId: Long): List<IShapeDTO> =
            mCanvases[canvasId]?.shapes?.map { it.asDTO }
                    ?: listOf()

    override fun getImages(canvasId: Long): List<IImageDTO> =
            mCanvases[canvasId]?.images?.map { it.asDTO }
                    ?: listOf()

    override fun saveShapes(canvasId: Long, shapes: List<IShapeDTO>) {
        mShapes.query()
                .equal(ShapeEntity_.canvasId, canvasId)
                .build()
                .remove()

        val canvas = mCanvases[canvasId]
        val entities = shapes.map {
            it.entity.apply { this.canvas.targetId = canvas.id }
        }
        mShapes.put(entities)
    }

    override fun saveImages(canvasId: Long, images: List<IImageDTO>) {
        mImages.query()
                .equal(ImageEntity_.canvasId, canvasId)
                .build()
                .remove()

        val canvas = mCanvases[canvasId]
        val entities = images.map {
            it.entity.apply { this.canvas.targetId = canvas.id }
        }
        mImages.put(entities)
    }

    override fun clearCanvas(canvasId: Long) {
        mShapes.query()
                .equal(ShapeEntity_.canvasId, canvasId)
                .build()
                .remove()

        mImages.query()
                .equal(ImageEntity_.canvasId, canvasId)
                .build()
                .remove()
    }

    override fun deleteCanvas(canvasId: Long) =
            mCanvases.remove(canvasId)

}
