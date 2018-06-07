package ru.iandreyshev.canvas.core

interface ICanvasObjectVisitor {

    fun visit(shape: CanvasShape)
    fun visit(image: CanvasImage)

}
