package ru.iandreyshev.adobeKiller.domain.adapter

import ru.iandreyshev.adobeKiller.domain.model.CanvasImage
import ru.iandreyshev.adobeKiller.domain.model.CanvasShape
import ru.iandreyshev.adobeKiller.domain.presentationModel.IModelSerializer
import ru.iandreyshev.localstorage.ILocalStorage

class CanvasStorageAdapter(
        private val canvasId: Long,
        localStorage: ILocalStorage
) : IModelSerializer {

    override fun serialize(shape: CanvasShape) {
    }

    override fun serialize(image: CanvasImage) {
    }

}
