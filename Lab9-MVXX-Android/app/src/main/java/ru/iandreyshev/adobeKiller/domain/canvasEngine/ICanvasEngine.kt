package ru.iandreyshev.adobeKiller.domain.canvasEngine

import ru.iandreyshev.adobeKiller.domain.file.IFile
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.IConstFrame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.IConstStyle

interface ICanvasEngine {

    fun observeChanges(observer: ((List<CanvasObject>) -> Unit)?)

    fun insert(type: ShapeType)
    fun insert(imageFile: IFile)
    fun delete(canvasObject: CanvasObject)
    fun clear()

    fun deserialize(serializer: ICanvasSerializer)
    fun serialize(serializer: ICanvasSerializer)

}
