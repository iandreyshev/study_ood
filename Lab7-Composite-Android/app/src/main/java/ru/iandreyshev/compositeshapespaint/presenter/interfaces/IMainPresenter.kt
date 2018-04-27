package ru.iandreyshev.compositeshapespaint.presenter.interfaces

import ru.iandreyshev.compositeshapespaint.model.shape.IShape
import ru.iandreyshev.gumballmachine.presenter.interfaces.IPresenter

interface IMainPresenter : IPresenter {
    fun draw(shapes: List<IShape>)

    fun setTarget(shape: IShape?)
}
