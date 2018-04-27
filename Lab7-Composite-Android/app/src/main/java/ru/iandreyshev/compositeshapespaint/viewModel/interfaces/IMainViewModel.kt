package ru.iandreyshev.compositeshapespaint.viewModel.interfaces

import ru.iandreyshev.compositeshapespaint.model.shape.IShape

interface IMainViewModel {
    fun draw(shapes: List<IShape>)

    fun setTarget(shape: IShape?)
}
