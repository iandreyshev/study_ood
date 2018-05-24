package ru.iandreyshev.adobeKiller.presentation.presenter

import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawable
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.ICanvasPresenter
import ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces.ICanvasViewModel

class CanvasPresenter(
        val viewModel: ICanvasViewModel
) : ICanvasPresenter {

    override fun setCanvasName(canvasName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setTarget(id: Long?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insert(id: Long, drawable: IDrawable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clear() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
