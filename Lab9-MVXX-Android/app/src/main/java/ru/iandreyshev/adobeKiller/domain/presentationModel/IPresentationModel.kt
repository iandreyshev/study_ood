package ru.iandreyshev.adobeKiller.domain.presentationModel

import ru.iandreyshev.adobeKiller.domain.file.IFile
import ru.iandreyshev.adobeKiller.domain.model.CanvasObject
import ru.iandreyshev.adobeKiller.domain.model.ShapeType
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.IConstFrame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.IConstStyle

interface IPresentationModel {

    fun observeChanges(observer: (() -> Unit)?)

    fun insert(type: ShapeType)
    fun insert(imageFile: IFile)
    fun update(canvasObject: CanvasObject, newFrame: IConstFrame)
    fun update(canvasObject: CanvasObject, newStyle: IConstStyle)
    fun delete(canvasObject: CanvasObject)
    fun clear()

    fun serialize(serializer: ICanvasSerializer)

}
