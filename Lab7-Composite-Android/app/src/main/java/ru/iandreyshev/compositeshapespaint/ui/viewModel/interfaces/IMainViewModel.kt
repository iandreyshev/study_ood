package ru.iandreyshev.compositeshapespaint.ui.viewModel.interfaces

import ru.iandreyshev.compositeshapespaint.model.shape.IShape

interface IMainViewModel : IProgressViewModel {
    fun updateShapes(shapes: List<IShape>)

    fun setTarget(shape: IShape?)

    fun beginNormal()

    fun beginGrouping()
}
