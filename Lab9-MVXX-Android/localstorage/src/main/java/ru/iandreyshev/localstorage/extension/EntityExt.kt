package ru.iandreyshev.localstorage.extension

import android.graphics.Bitmap
import ru.iandreyshev.localstorage.entity.*

internal val ICanvasEntity.entity: CanvasEntity
    get() = CanvasEntity(
            id = id,
            name = name
    )

internal val CanvasEntity.publicEntity: ICanvasEntity
    get() = object : ICanvasEntity {
        override val id: Long = this@publicEntity.id
        override val name: String = this@publicEntity.name
    }

internal val IShapeEntity.entity: ShapeEntity
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

internal val ShapeEntity.publicEntity: IShapeEntity
    get() = object : IShapeEntity {
        override val type: Int = this@publicEntity.type
        override val x: Float = this@publicEntity.x
        override val y: Float = this@publicEntity.y
        override val width: Float = this@publicEntity.width
        override val height: Float = this@publicEntity.height
        override val stroke: Float = this@publicEntity.stroke
        override val strokeColor: Int = this@publicEntity.strokeColor
        override val fillColor: Int = this@publicEntity.fillColor
    }

internal val IImageEntity.entity: ImageEntity
    get() = ImageEntity(
            x = x,
            y = y,
            width = width,
            height = height,
            image = image
    )

internal val ImageEntity.publicEntity: IImageEntity
    get() = object : IImageEntity {
        override val x: Float = this@publicEntity.x
        override val y: Float = this@publicEntity.y
        override val width: Float = this@publicEntity.width
        override val height: Float = this@publicEntity.height
        override val image: Bitmap = this@publicEntity.image
    }
