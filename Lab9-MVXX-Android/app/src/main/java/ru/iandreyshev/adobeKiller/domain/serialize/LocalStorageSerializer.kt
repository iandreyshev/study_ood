package ru.iandreyshev.adobeKiller.domain.serialize

import ru.iandreyshev.adobeKiller.domain.extension.toEntity
import ru.iandreyshev.adobeKiller.domain.model.ICanvasObjectVisitor
import ru.iandreyshev.adobeKiller.domain.model.ImageData
import ru.iandreyshev.adobeKiller.domain.model.ShapeData
import ru.iandreyshev.localstorage.entity.IImageDTO
import ru.iandreyshev.localstorage.entity.IShapeDTO

class LocalStorageSerializer : ICanvasObjectVisitor {

    private val mShapes = mutableListOf<IShapeDTO>()
    private val mImages = mutableListOf<IImageDTO>()

    val shapes: List<IShapeDTO>
        get() = mShapes
    val images: List<IImageDTO>
        get() = mImages

    override fun visit(shape: ShapeData) {
        mShapes.add(shape.toEntity())
    }

    override fun visit(image: ImageData) {
        mImages.add(image.toEntity())
    }

    fun clear() {
        mShapes.clear()
        mImages.clear()
    }

}
