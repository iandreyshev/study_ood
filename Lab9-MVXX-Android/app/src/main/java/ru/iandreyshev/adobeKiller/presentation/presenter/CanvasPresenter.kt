package ru.iandreyshev.adobeKiller.presentation.presenter

import ru.iandreyshev.adobeKiller.domain.model.CanvasObject
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.ICanvasPresenter
import ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces.ICanvasViewModel

class CanvasPresenter(
        canvasName: String,
        private val viewModel: ICanvasViewModel
) : ICanvasPresenter {

    init {
        viewModel.setCanvasName(canvasName)
    }

    override fun setTarget(canvasObject: CanvasObject?) =
            viewModel.setTarget(canvasObject)

    override fun insert(canvasObject: CanvasObject) =
            viewModel.insert(canvasObject)

    override fun invalidate() =
            viewModel.invalidate()

    override fun clear() =
            viewModel.clear()

}
