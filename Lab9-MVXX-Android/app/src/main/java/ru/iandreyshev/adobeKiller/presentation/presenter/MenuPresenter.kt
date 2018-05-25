package ru.iandreyshev.adobeKiller.presentation.presenter

import ru.iandreyshev.adobeKiller.domain.model.CanvasData
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.IMenuPresenter
import ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces.IMenuViewModel

class MenuPresenter(
        private val viewModel: IMenuViewModel
) : IMenuPresenter {

    override fun openCanvas() =
            viewModel.openCanvas()

    override fun setCanvases(canvases: List<CanvasData>) =
            viewModel.setCanvases(canvases)

}
