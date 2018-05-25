package ru.iandreyshev.localstorage.extension

import android.graphics.Bitmap
import ru.iandreyshev.localstorage.entity.*

internal val ICanvasDTO.entity: CanvasEntity
    get() = CanvasEntity(
            id = id,
            name = name
    )

internal val CanvasEntity.asDTO: ICanvasDTO
    get() = object : ICanvasDTO {
        override val id: Long = this@asDTO.id
        override val name: String = this@asDTO.name
    }

internal val IShapeDTO.entity: ShapeEntity
    get() = ShapeEntity(
            x = x,
            y = y,
            type = type,
            width = width,
            height = height,
            stroke = stroke,
            strokeColor = strokeColor,
            fillColor = fillColor
    )

internal val ShapeEntity.asDTO: IShapeDTO
    get() = object : IShapeDTO {
        override val type: Int = this@asDTO.type
        override val x: Float = this@asDTO.x
        override val y: Float = this@asDTO.y
        override val width: Float = this@asDTO.width
        override val height: Float = this@asDTO.height
        override val stroke: Float = this@asDTO.stroke
        override val strokeColor: Int = this@asDTO.strokeColor
        override val fillColor: Int = this@asDTO.fillColor
    }

internal val IImageDTO.entity: ImageEntity
    get() = ImageEntity(
            x = x,
            y = y,
            width = width,
            height = height,
            image = image
    )

internal val ImageEntity.asDTO: IImageDTO
    get() = object : IImageDTO {
        override val x: Float = this@asDTO.x
        override val y: Float = this@asDTO.y
        override val width: Float = this@asDTO.width
        override val height: Float = this@asDTO.height
        override val image: Bitmap = this@asDTO.image
    }
