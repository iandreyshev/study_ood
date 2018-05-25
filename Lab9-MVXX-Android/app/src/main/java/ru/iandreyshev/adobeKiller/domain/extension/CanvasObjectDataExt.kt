package ru.iandreyshev.adobeKiller.domain.extension

import android.graphics.Bitmap
import ru.iandreyshev.adobeKiller.domain.model.CanvasData
import ru.iandreyshev.adobeKiller.domain.model.ImageData
import ru.iandreyshev.adobeKiller.domain.model.ShapeData
import ru.iandreyshev.adobeKiller.domain.model.ShapeType
import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.Color
import ru.iandreyshev.adobeKiller.presentation.drawing.container.Vec2f
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.ImageStyle
import ru.iandreyshev.adobeKiller.presentation.drawing.style.Style
import ru.iandreyshev.localstorage.entity.ICanvasDTO
import ru.iandreyshev.localstorage.entity.IImageDTO
import ru.iandreyshev.localstorage.entity.IShapeDTO

internal fun ICanvasDTO.toModel(): CanvasData = with(this) {
    CanvasData(
            id = id,
            name = name
    )
}

internal fun ShapeData.toEntity(): IShapeDTO = with(this) {
    object : IShapeDTO {
        override val type: Int = this@toEntity.type.ordinal
        override val x: Float = frame.position.x
        override val y: Float = frame.position.y
        override val width: Float = frame.width
        override val height: Float = frame.height
        override val stroke: Float = style.strokeSize
        override val strokeColor: Int = style.strokeColor.ordinal
        override val fillColor: Int = style.fillColor.ordinal
    }
}

internal fun ImageData.toEntity(): IImageDTO = with(this) {
    object : IImageDTO {
        override val x: Float = frame.position.x
        override val y: Float = frame.position.y
        override val width: Float = frame.width
        override val height: Float = frame.height
        override val image: Bitmap = this@with.image
    }
}

internal fun IImageDTO.toModel(): ImageData = with(this) {
    ImageData(
            frame = Frame(Vec2f(x, y), width, height),
            style = ImageStyle(),
            image = this@toModel.image
    )
}

internal fun IShapeDTO.toModel(): ShapeData = with(this) {
    ShapeData(
            type = ShapeType.values()[this@toModel.type],
            frame = Frame(Vec2f(x, y), width, height),
            style = Style(
                    fillColor = Color.values()[fillColor],
                    strokeColor = Color.values()[strokeColor],
                    strokeSize = stroke)

    )
}
