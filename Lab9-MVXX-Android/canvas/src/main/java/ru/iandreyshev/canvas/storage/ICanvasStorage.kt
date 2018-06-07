package ru.iandreyshev.canvas.storage

import ru.iandreyshev.canvas.core.CanvasObject

interface ICanvasStorage {

    fun serialize(objects: List<CanvasObject>)
    fun deserialize(): List<CanvasObject>

}
