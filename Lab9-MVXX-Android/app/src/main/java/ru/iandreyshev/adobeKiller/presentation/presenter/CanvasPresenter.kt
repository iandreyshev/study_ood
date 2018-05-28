package ru.iandreyshev.adobeKiller.presentation.presenter

import ru.iandreyshev.adobeKiller.domain.model.CanvasObject
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawable
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.ICanvasPresenter
import ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces.ICanvasViewModel

class CanvasPresenter(
        val viewModel: ICanvasViewModel
) : ICanvasPresenter {

    override fun setTitle(canvasName: String) =
            viewModel.setCanvasName(canvasName)

    override fun setTarget(canvasObject: CanvasObject?) =
            viewModel.setTarget(canvasObject)

    override fun insert(canvasObject: CanvasObject) =
            viewModel.insert(canvasObject)

    override fun clear() =
            viewModel.clear()

}
