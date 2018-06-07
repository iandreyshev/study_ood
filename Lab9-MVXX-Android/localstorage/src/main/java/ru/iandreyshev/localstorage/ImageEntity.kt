package ru.iandreyshev.localstorage

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import ru.iandreyshev.canvas.core.CanvasImage

@Entity
internal class ImageEntity(
        @Id
        var id: Long = 0,
        var x: Float = 0f,
        var y: Float = 0f,
        var width: Float = 0f,
        var height: Float = 0f,
        var imageBytes: ByteArray = byteArrayOf()
) {

    constructor(image: CanvasImage) : this(
            x = image.frame.x,
            y = image.frame.y,
            width = image.frame.width,
            height = image.frame.height,
            imageBytes = image.imageFile.bytes()
    )

}
