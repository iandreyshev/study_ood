package ru.iandreyshev.canvas.core

import ru.iandreyshev.canvas.file.IFile
import ru.iandreyshev.canvas.presenter.ICanvasPresenter

interface ICanvas {

    fun setPresenter(presenter: ICanvasPresenter)

    fun insert(type: ShapeType)
    fun insert(imageFile: IFile)
    fun update()
    fun clear()

    fun load()
    fun save()

}
