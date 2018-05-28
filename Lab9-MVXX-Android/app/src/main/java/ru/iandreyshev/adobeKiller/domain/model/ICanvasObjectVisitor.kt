package ru.iandreyshev.adobeKiller.domain.model

interface ICanvasObjectVisitor {

    fun visit(shape: ShapeObject) = Unit
    fun visit(image: ImageObject) = Unit

}
