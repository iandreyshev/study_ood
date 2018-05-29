package ru.iandreyshev.adobeKiller.domain.presentationModel

import ru.iandreyshev.adobeKiller.domain.model.CanvasObject

interface ICanvasSerializer {

    fun serialize(objects: List<CanvasObject>)

}
