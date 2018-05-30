package ru.iandreyshev.adobeKiller.domain.canvasEngine

interface ICanvasSerializer {

    fun serialize(objects: List<CanvasObject>)
    fun deserialize(): List<CanvasObject>

}
