package ru.iandreyshev.adobeKiller.domain.presentationModel

import ru.iandreyshev.adobeKiller.domain.file.IFile
import ru.iandreyshev.adobeKiller.domain.model.CanvasObject
import ru.iandreyshev.adobeKiller.domain.model.ShapeType

interface IPresentationModel {

    fun observeChanges(observer: (() -> Unit)?)

    fun insert(type: ShapeType)
    fun insert(imageFile: IFile)
    fun delete(canvasObject: CanvasObject)
    fun clear()

    fun serialize(serializer: IModelSerializer)

}
