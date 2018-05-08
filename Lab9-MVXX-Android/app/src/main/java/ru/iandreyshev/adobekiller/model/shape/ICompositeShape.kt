package ru.iandreyshev.adobekiller.model.shape

interface ICompositeShape : IShape {
    fun add(shape: IShape)

    fun remove(shape: IShape)
}
