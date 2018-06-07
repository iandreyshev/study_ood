package ru.iandreyshev.adobeKiller.di

import ru.iandreyshev.adobeKiller.di.factory.IPresenterFactory
import ru.iandreyshev.adobeKiller.model.ICanvasAppModel
import ru.iandreyshev.adobeKiller.presentation.presenter.CanvasAppModelPresenter
import ru.iandreyshev.adobeKiller.presentation.presenter.CanvasPresenter
import ru.iandreyshev.adobeKiller.presentation.viewModel.ICanvasViewModel
import ru.iandreyshev.canvas.presenter.ICanvasPresenter

class PresenterFactory : IPresenterFactory {

    override fun getCanvasAppModelPresenter(viewModel: ICanvasViewModel): ICanvasAppModel.IPresenter =
            CanvasAppModelPresenter(
                    viewModel = viewModel
            )

    override fun getCanvasPresenter(viewModel: ICanvasViewModel): ICanvasPresenter =
            CanvasPresenter(
                    viewModel = viewModel
            )

}
