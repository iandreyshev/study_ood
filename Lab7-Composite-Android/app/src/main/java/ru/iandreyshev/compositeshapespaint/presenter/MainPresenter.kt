package ru.iandreyshev.compositeshapespaint.presenter

import ru.iandreyshev.compositeshapespaint.model.shape.IShape
import ru.iandreyshev.compositeshapespaint.presenter.interfaces.IMainPresenter
import ru.iandreyshev.compositeshapespaint.viewModel.interfaces.IMainViewModel

class MainPresenter(
        val viewModel: IMainViewModel
) : IMainPresenter {

    override fun draw(shapes: List<IShape>) =
            viewModel.draw(shapes)

    override fun setTarget(shape: IShape?) =
            viewModel.setTarget(shape)
}
