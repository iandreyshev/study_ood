package ru.iandreyshev.adobeKiller.presentation.presenter

import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawable
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.ICanvasPresenter
import ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces.ICanvasViewModel

class CanvasPresenter(
        val viewModel: ICanvasViewModel
) : ICanvasPresenter {

    override fun setCanvasName(canvasName: String) =
            viewModel.setCanvasName(canvasName)

    override fun setTarget(id: Long?) =
            viewModel.setTarget(id)

    override fun insert(drawable: IDrawable) =
            viewModel.insert(drawable)

    override fun clear() =
            viewModel.clear()

}
