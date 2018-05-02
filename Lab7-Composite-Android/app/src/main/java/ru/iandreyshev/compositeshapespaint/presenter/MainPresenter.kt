package ru.iandreyshev.compositeshapespaint.presenter

import ru.iandreyshev.compositeshapespaint.model.shape.IShape
import ru.iandreyshev.compositeshapespaint.presenter.interfaces.IMainPresenter
import ru.iandreyshev.compositeshapespaint.presenter.interfaces.IProgressPresenter
import ru.iandreyshev.compositeshapespaint.ui.viewModel.interfaces.IMainViewModel

class MainPresenter(
        val viewModel: IMainViewModel
) : IMainPresenter, IProgressPresenter by ProgressController(viewModel) {

    override fun updateShapes(shapes: List<IShape>) =
            viewModel.updateShapes(shapes)

    override fun setTarget(shape: IShape?) =
            viewModel.setTarget(shape)

    override fun setState(stateName: String) {
        when (stateName) {
            "normal" -> viewModel.beginNormal()
            "grouping" -> viewModel.beginGrouping()
        }
    }
}
