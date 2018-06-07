package ru.iandreyshev.canvas.core

import ru.iandreyshev.canvas.file.IFile

interface ICanvasObjectFactory {

    fun createShape(shapeType: ShapeType): CanvasObject
    fun createImage(file: IFile): CanvasObject

}
