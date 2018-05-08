package ru.iandreyshev.adobekiller.presenter

import ru.iandreyshev.adobekiller.model.shape.IShape
import ru.iandreyshev.adobekiller.presenter.interfaces.IMainPresenter
import ru.iandreyshev.adobekiller.presenter.interfaces.IProgressPresenter
import ru.iandreyshev.adobekiller.ui.viewModel.interfaces.IMainViewModel

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
