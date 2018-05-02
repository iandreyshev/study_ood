package ru.iandreyshev.compositeshapespaint.model.shape

import ru.iandreyshev.compositeshapespaint.model.shape.IShape

interface IShapeFactory {
    fun create(shapeName: String): IShape?
}
