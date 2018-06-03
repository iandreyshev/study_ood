package ru.iandreyshev.adobeKiller.domain.canvasEngine

import ru.iandreyshev.adobeKiller.domain.file.IFile
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.IConstFrame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.IConstStyle

interface ICanvasEngine {

    fun observeUpdate(observer: ((List<CanvasObject>?) -> Unit)?)

    fun insert(type: ShapeType)
    fun insert(imageFile: IFile)
    fun clear()

    fun deserialize(serializer: ICanvasSerializer)
    fun serialize(serializer: ICanvasSerializer)

}
