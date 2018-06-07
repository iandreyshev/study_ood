package ru.iandreyshev.adobeKiller.presentation.presenter

import ru.iandreyshev.adobeKiller.model.ICanvasAppModel
import ru.iandreyshev.adobeKiller.presentation.viewModel.ICanvasViewModel

class CanvasAppModelPresenter(
        private val viewModel: ICanvasViewModel
) : ICanvasAppModel.IPresenter {

    override fun resetTarget() {
        viewModel.target.postValue(null)
    }

}
