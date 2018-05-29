package ru.iandreyshev.adobeKiller.domain.presentationModel

import ru.iandreyshev.adobeKiller.domain.model.CanvasShape
import ru.iandreyshev.adobeKiller.domain.model.CanvasImage

interface IModelSerializer {

    fun serialize(shape: CanvasShape)
    fun serialize(image: CanvasImage)

}
