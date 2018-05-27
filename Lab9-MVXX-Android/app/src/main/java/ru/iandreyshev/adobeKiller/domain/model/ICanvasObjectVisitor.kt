package ru.iandreyshev.adobeKiller.domain.model

interface ICanvasObjectVisitor {

    fun visit(shape: ShapeData)
    fun visit(image: ImageData)

}
