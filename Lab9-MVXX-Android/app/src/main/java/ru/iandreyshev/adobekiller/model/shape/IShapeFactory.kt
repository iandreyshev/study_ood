package ru.iandreyshev.adobekiller.model.shape

import ru.iandreyshev.adobekiller.model.shape.IShape

interface IShapeFactory {
    fun create(shapeName: String): IShape?
}
