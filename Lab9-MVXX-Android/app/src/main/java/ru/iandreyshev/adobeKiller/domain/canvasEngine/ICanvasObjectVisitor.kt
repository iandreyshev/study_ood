package ru.iandreyshev.adobeKiller.domain.canvasEngine

interface ICanvasObjectVisitor {

    fun visit(shape: CanvasShape)
    fun visit(image: CanvasImage)

}
