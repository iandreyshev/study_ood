package ru.iandreyshev.compositeshapespaint.model.shape

interface ICompositeShape : IShape {
    fun add(shape: IShape)

    fun remove(shape: IShape)
}
