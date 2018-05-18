package ru.iandreyshev.adobeKiller.presentation.presenter

import ru.iandreyshev.adobeKiller.domain.model.CanvasData
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawable
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.ICanvasPresenter
import ru.iandreyshev.adobeKiller.presentation.ui.viewModel.interfaces.ICanvasViewModel

class CanvasPresenter(
        val viewModel: ICanvasViewModel
) : ICanvasPresenter {

    override fun setCanvasData(canvasData: CanvasData) =
            viewModel.setCanvasData(canvasData)

    override fun updateShapes(shapes: List<IDrawable>) =
            viewModel.updateShapes(shapes)

    override fun setTarget(shape: IDrawable?) =
            viewModel.setTarget(shape)

}
