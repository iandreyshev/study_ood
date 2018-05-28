package ru.iandreyshev.adobeKiller.domain.extension

import ru.iandreyshev.adobeKiller.domain.file.FileWrapper
import ru.iandreyshev.adobeKiller.domain.model.CanvasData
import ru.iandreyshev.adobeKiller.domain.model.ImageObject
import ru.iandreyshev.adobeKiller.domain.model.ShapeObject
import ru.iandreyshev.adobeKiller.domain.model.ShapeType
import ru.iandreyshev.adobeKiller.domain.presentationModel.ICanvasObjectModel
import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.Color
import ru.iandreyshev.adobeKiller.presentation.drawing.container.Vec2f
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.Style
import ru.iandreyshev.localstorage.entity.ICanvasDTO
import ru.iandreyshev.localstorage.entity.IImageDTO
import ru.iandreyshev.localstorage.entity.IShapeDTO
import java.io.File

internal fun ICanvasDTO.toModel(): CanvasData = with(this) {
    CanvasData(
            id = id,
            name = name
    )
}

internal fun ShapeObject.toEntity(): IShapeDTO = with(this) {
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

internal fun ImageObject.toEntity(): IImageDTO = with(this) {
    object : IImageDTO {
        override val x: Float = frame.position.x
        override val y: Float = frame.position.y
        override val width: Float = frame.width
        override val height: Float = frame.height
        override val imagePath: String = imageFile.path
    }
}

internal fun IImageDTO.toModel(model: ICanvasObjectModel): ImageObject = with(this) {
    ImageObject(
            frame = Frame(Vec2f(x, y), width, height),
            model = model,
            imageFile = FileWrapper(File(imagePath))
    )
}

internal fun IShapeDTO.toModel(model: ICanvasObjectModel): ShapeObject = with(this) {
    ShapeObject(
            frame = Frame(Vec2f(x, y), width, height),
            style = Style(
                    fillColor = Color.values()[fillColor],
                    strokeColor = Color.values()[strokeColor],
                    strokeSize = stroke),
            model = model,
            type = ShapeType.values()[this@toModel.type]

    )
}
