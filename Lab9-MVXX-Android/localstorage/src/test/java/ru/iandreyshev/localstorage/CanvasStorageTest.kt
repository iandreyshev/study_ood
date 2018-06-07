package ru.iandreyshev.localstorage

import org.junit.Test
import pl.mareklangiewicz.uspek.USpek
import pl.mareklangiewicz.uspek.USpek.o
import pl.mareklangiewicz.uspek.eq
import ru.iandreyshev.canvas.core.CanvasImage
import ru.iandreyshev.canvas.core.CanvasShape
import ru.iandreyshev.canvas.core.ICanvasObjectVisitor
import ru.iandreyshev.canvas.core.ShapeType
import ru.iandreyshev.canvas.file.IFile
import ru.iandreyshev.canvas.style.Color
import ru.iandreyshev.canvas.style.Style
import ru.iandreyshev.geometry.frame.Frame
import ru.iandreyshev.geometry.vector.Vec2f
import java.io.File

class CanvasStorageTest {

    companion object {
        private val frame = Frame(
                position = Vec2f(10, 10),
                width = 20f,
                height = 20f
        )

        private val style = Style(
                fillColor = Color.WHITE,
                strokeColor = Color.BLUE,
                strokeSize = 30f
        )

        private val shape = CanvasShape(
                frame = frame,
                style = style,
                type = ShapeType.Rect
        )
    }

    @Test
    fun test() = USpek.uspek("Canvas storage test") {
        "Can save " o {

            val tempFile = File.createTempFile("object-store-test", "")
            tempFile.delete()
            val mBoxStore = MyObjectBox.builder().directory(tempFile).build()
            val storage = CanvasStorage(mBoxStore)

            "deserializedShape type" o {
                storage.serialize(listOf(shape))
                val deserializedShape = storage.deserialize().first()
                var type: ShapeType? = null
                val visitor = object : ICanvasObjectVisitor {
                    override fun visit(shape: CanvasShape) {
                        type = shape.type
                    }

                    override fun visit(image: CanvasImage) = Unit
                }
                deserializedShape.accept(visitor)
                type eq ShapeType.Rect
            }

            "frame properties" o {
                storage.serialize(listOf(shape))
                val deserializedShape = storage.deserialize().first()

                deserializedShape.frame.x eq frame.x
                deserializedShape.frame.y eq frame.y
                deserializedShape.frame.width eq frame.width
                deserializedShape.frame.height eq frame.height
            }

            "style properties" o {
                storage.serialize(listOf(shape))
                val deserializedShape = storage.deserialize().first()

                deserializedShape.style.fillColor eq style.fillColor
                deserializedShape.style.strokeColor eq style.strokeColor
                deserializedShape.style.strokeSize eq style.strokeSize
            }

            "image bytes" o {
                val bytesCount = 1000
                val bytes = ByteArray(bytesCount) { it.toByte() }
                val imageFile = FileWrap(bytes)
                val image = CanvasImage(frame, imageFile)
                storage.serialize(listOf(image))

                val deserializedBytes = mutableListOf<Byte>()
                val visitor = object : ICanvasObjectVisitor {
                    override fun visit(shape: CanvasShape) = Unit
                    override fun visit(image: CanvasImage) {
                        image.imageFile.bytes().forEach { byte -> deserializedBytes.add(byte) }
                    }
                }

                val deserializedObjects = storage.deserialize()
                val deserializedImage = deserializedObjects.first()
                deserializedImage.accept(visitor)

                deserializedBytes.size eq bytesCount
                repeat(bytesCount) {
                    it.toByte() eq deserializedBytes[it]
                }
            }
        }
    }

    private inner class FileWrap(private val bytes: ByteArray) : IFile {
        override fun bytes(): ByteArray = bytes
        override fun flush() = Unit
        override fun delete() = Unit
    }

}
