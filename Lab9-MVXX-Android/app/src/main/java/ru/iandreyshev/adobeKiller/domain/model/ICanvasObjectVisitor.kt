package ru.iandreyshev.adobeKiller.domain.model

interface ICanvasObjectVisitor {

    fun visit(shape: CanvasShape) = Unit
    fun visit(image: CanvasImage) = Unit

}
