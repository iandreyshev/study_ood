package ru.iandreyshev.adobeKiller.domain.adapter

import ru.iandreyshev.adobeKiller.domain.extension.toEntity
import ru.iandreyshev.adobeKiller.domain.model.CanvasImage
import ru.iandreyshev.adobeKiller.domain.model.CanvasObject
import ru.iandreyshev.adobeKiller.domain.model.CanvasShape
import ru.iandreyshev.adobeKiller.domain.model.ICanvasObjectVisitor
import ru.iandreyshev.adobeKiller.domain.presentationModel.ICanvasSerializer
import ru.iandreyshev.localstorage.ILocalStorage

class CanvasStorageAdapter(
        private val localStorage: ILocalStorage
) : ICanvasSerializer, ILocalStorage by localStorage {

    var canvasId: Long? = null

    override fun serialize(objects: List<CanvasObject>) {
        canvasId?.let { id ->

            localStorage.clearCanvas(id)

            val storage = SaveToLocalVisitor(id, localStorage)
            objects.forEach { it.accept(storage) }

        }
    }

    private class SaveToLocalVisitor(
            private val canvasId: Long,
            private val localStorage: ILocalStorage
    ) : ICanvasObjectVisitor {

        override fun visit(image: CanvasImage) {
            localStorage.saveImages(canvasId, listOf(image.toEntity()))
        }

        override fun visit(shape: CanvasShape) {
            localStorage.saveShapes(canvasId, listOf(shape.toEntity()))
        }

    }

}
