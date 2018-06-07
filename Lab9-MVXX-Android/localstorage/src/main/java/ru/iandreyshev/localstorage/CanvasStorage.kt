package ru.iandreyshev.localstorage

import io.objectbox.BoxStore
import ru.iandreyshev.canvas.core.*
import ru.iandreyshev.canvas.file.FileWrapper
import ru.iandreyshev.canvas.storage.ICanvasStorage
import ru.iandreyshev.canvas.style.Color
import ru.iandreyshev.canvas.style.Style
import ru.iandreyshev.geometry.frame.Frame
import ru.iandreyshev.geometry.vector.Vec2f
import ru.iandreyshev.localstorage.entity.*

class CanvasStorage(boxStore: BoxStore) : ICanvasStorage {

    private val mShapes = boxStore.boxFor(ShapeEntity::class.java)
    private val mImages = boxStore.boxFor(ImageEntity::class.java)

    override fun deserialize(): List<CanvasObject> {
        val result = mutableListOf<CanvasObject>()
        result.addAll(mShapes.all.map { it.toModel() })
        result.addAll(mImages.all.map { it.toModel() })
        return result
    }

    override fun serialize(objects: List<CanvasObject>) {
        val serializer = Serializer()
        objects.forEach { it.accept(serializer) }
    }

    private inner class Serializer : ICanvasObjectVisitor {
        override fun visit(shape: CanvasShape) {
            mShapes.put(ShapeEntity(shape))
        }

        override fun visit(image: CanvasImage) {
            mImages.put(ImageEntity(image))
        }
    }

    private fun ShapeEntity.toModel(): CanvasShape {
        return CanvasShape(
                frame = Frame(
                        position = Vec2f(
                                x = x,
                                y = y),
                        width = width,
                        height = height
                ),
                style = Style(
                        fillColor = Color.values()[fillColor],
                        strokeColor = Color.values()[strokeColor],
                        strokeSize = stroke
                ),
                type = ShapeType.values()[type]
        )
    }

    private fun ImageEntity.toModel(): CanvasImage {
        return CanvasImage(
                frame = Frame(
                        position = Vec2f(
                                x = x,
                                y = y),
                        width = width,
                        height = height
                ),
                imageFile = FileWrapper(
                        path = imagePath
                )
        )
    }

}
