package ru.iandreyshev.adobekiller.ui.viewModel.interfaces

import ru.iandreyshev.adobekiller.model.shape.IShape

interface IMainViewModel : IProgressViewModel {
    fun updateShapes(shapes: List<IShape>)

    fun setTarget(shape: IShape?)

    fun beginNormal()

    fun beginGrouping()
}
