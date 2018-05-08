package ru.iandreyshev.adobekiller.presenter.interfaces

import ru.iandreyshev.adobekiller.model.shape.IShape

interface IMainPresenter : IProgressPresenter {
    fun updateShapes(shapes: List<IShape>)

    fun setTarget(shape: IShape?)

    fun setState(stateName: String)
}
