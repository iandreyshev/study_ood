package ru.iandreyshev.adobeKiller.model

import ru.iandreyshev.canvas.core.ShapeType
import java.io.File

interface ICanvasAppModel {

    fun setPresenter(presenter: IPresenter)

    fun insert(shape: ShapeType)
    fun insert(image: File)

    fun undo()
    fun redo()

    fun refresh()
    fun save()

    interface IPresenter {
        fun resetTarget()
    }

}
