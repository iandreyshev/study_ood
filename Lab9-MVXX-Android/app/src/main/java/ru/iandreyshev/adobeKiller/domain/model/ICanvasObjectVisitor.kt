package ru.iandreyshev.adobeKiller.domain.model

interface ICanvasObjectVisitor {

    fun visit(shape: CanvasShape)
    fun visit(image: CanvasImage)

}
