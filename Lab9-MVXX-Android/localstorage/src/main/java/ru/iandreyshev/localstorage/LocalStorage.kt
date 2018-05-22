package ru.iandreyshev.localstorage

import android.content.Context
import io.objectbox.Box
import io.objectbox.Property
import io.objectbox.query.Query
import io.objectbox.query.QueryBuilder
import ru.iandreyshev.localstorage.entity.*
import ru.iandreyshev.localstorage.extension.entity
import ru.iandreyshev.localstorage.extension.publicEntity

class LocalStorage(
        context: Context
) : ILocalStorage {

    private val mCanvases: Box<CanvasEntity>
    private val mShapes: Box<ShapeEntity>
    private val mImages: Box<ImageEntity>

    init {
        val memory = MyObjectBox.builder()
                .androidContext(context)
                .build()

        mCanvases = memory.boxFor(CanvasEntity::class.java)
        mShapes = memory.boxFor(ShapeEntity::class.java)
        mImages = memory.boxFor(ImageEntity::class.java)
    }

    override fun createCanvas(name: String): ICanvasEntity =
            CanvasEntity(name = name).apply {
                mCanvases.put(this)
            }.publicEntity

    override fun getCanvases(): List<ICanvasEntity> =
            mCanvases.all.map { it.publicEntity }

    override fun getShapes(canvasId: Long): List<IShapeEntity> =
            mShapes.find(ShapeEntity_.canvasId, canvasId)
                    .map { it.publicEntity }

    override fun getImages(canvasId: Long): List<IImageEntity> =
            mImages.find(ImageEntity_.canvasId, canvasId)
                    .map { it.publicEntity }

    override fun saveShapes(canvasId: Long, shapes: List<IShapeEntity>) {
        mShapes.remove(ShapeEntity_.canvasId, canvasId)

        val canvas = mCanvases[canvasId]
        mShapes.put(shapes.map {
            it.entity.apply { this.canvas.target = canvas }
        })
    }

    override fun saveImages(canvasId: Long, images: List<IImageEntity>) {
        mImages.remove(ImageEntity_.canvasId, canvasId)

        val canvas = mCanvases[canvasId]
        mImages.put(images.map {
            it.entity.apply { this.canvas.target = canvas }
        })
    }

    override fun deleteCanvas(canvasId: Long) =
            mCanvases.remove(canvasId)

    private fun <T> Box<T>.query(queryAction: QueryBuilder<T>.() -> Unit): Query<T> =
            query().apply(queryAction).build()

    private fun <T> Box<T>.remove(property: Property, value: Long) =
            query { find(property, value) }.remove()

}
