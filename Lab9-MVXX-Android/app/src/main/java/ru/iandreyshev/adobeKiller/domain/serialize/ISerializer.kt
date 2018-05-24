package ru.iandreyshev.adobeKiller.domain.serialize

import ru.iandreyshev.adobeKiller.domain.model.ImageData
import ru.iandreyshev.adobeKiller.domain.model.ShapeData

interface ISerializer {

    fun serialize(shape: ShapeData)
    fun serialize(image: ImageData)

}
