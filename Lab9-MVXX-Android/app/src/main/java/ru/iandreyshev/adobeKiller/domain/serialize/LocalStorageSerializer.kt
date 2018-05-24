package ru.iandreyshev.adobeKiller.domain.serialize

import ru.iandreyshev.adobeKiller.domain.extension.toEntity
import ru.iandreyshev.adobeKiller.domain.model.ImageData
import ru.iandreyshev.adobeKiller.domain.model.ShapeData
import ru.iandreyshev.localstorage.entity.IImageEntity
import ru.iandreyshev.localstorage.entity.IShapeEntity

class LocalStorageSerializer : ISerializer {

    private val mShapes = mutableListOf<IShapeEntity>()
    private val mImages = mutableListOf<IImageEntity>()

    val shapes: List<IShapeEntity>
        get() = mShapes
    val images: List<IImageEntity>
        get() = mImages

    override fun serialize(shape: ShapeData) {
        mShapes.add(shape.toEntity())
    }

    override fun serialize(image: ImageData) {
        mImages.add(image.toEntity())
    }

    fun clear() {
        mShapes.clear()
        mImages.clear()
    }

}
