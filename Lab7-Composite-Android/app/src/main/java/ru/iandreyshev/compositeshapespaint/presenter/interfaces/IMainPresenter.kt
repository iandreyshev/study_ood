package ru.iandreyshev.compositeshapespaint.presenter.interfaces

import ru.iandreyshev.compositeshapespaint.model.shape.IShape

interface IMainPresenter : IProgressPresenter {
    fun updateShapes(shapes: List<IShape>)

    fun setTarget(shape: IShape?)

    fun setState(stateName: String)
}
