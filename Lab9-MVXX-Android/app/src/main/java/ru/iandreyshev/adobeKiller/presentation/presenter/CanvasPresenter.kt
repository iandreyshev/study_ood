package ru.iandreyshev.adobeKiller.presentation.presenter

import ru.iandreyshev.adobeKiller.domain.model.CanvasObject
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawable
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.ICanvasPresenter
import ru.iandreyshev.adobeKiller.presentation.ui.targetFrame.ITargetCanvasObject
import ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces.ICanvasViewModel

class CanvasPresenter(
        private val viewModel: ICanvasViewModel,
        private val targetFactory: ITargetFactory,
        private val drawableFactory: IDrawableFactory
) : ICanvasPresenter {

    override fun setTarget(canvasObject: CanvasObject?) {
        if (canvasObject == null) {
            viewModel.setTarget(null)
            return
        }

        val target = targetFactory.create(canvasObject)
        viewModel.setTarget(target)
    }

    override fun insert(canvasObject: CanvasObject) {
        val drawable = drawableFactory.create(canvasObject)
        viewModel.insert(drawable)
    }

    override fun invalidate() =
            viewModel.invalidate()

    override fun clear() =
            viewModel.clear()

    interface ITargetFactory {
        fun create(canvasObject: CanvasObject): ITargetCanvasObject
    }

    interface IDrawableFactory {
        fun create(canvasObject: CanvasObject): IDrawable
    }

}
