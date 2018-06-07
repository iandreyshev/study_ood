package ru.iandreyshev.localstorage.entity

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import ru.iandreyshev.canvas.core.CanvasShape

@Entity
internal open class ShapeEntity(
        @Id
        var id: Long = 0,
        var type: Int = 0,
        var x: Float = 0f,
        var y: Float = 0f,
        var width: Float = 0f,
        var height: Float = 0f,
        var stroke: Float = 0f,
        var strokeColor: Int = 0,
        var fillColor: Int = 0
) {

    constructor(shape: CanvasShape) : this(
            type = shape.type.ordinal,
            x = shape.frame.x,
            y = shape.frame.y,
            width = shape.frame.width,
            height = shape.frame.height,
            stroke = shape.style.strokeSize,
            strokeColor = shape.style.strokeColor.ordinal,
            fillColor = shape.style.fillColor.ordinal
    )

}
