package ru.iandreyshev.adobeKiller.di.factory

import ru.iandreyshev.adobeKiller.model.ICanvasAppModel
import ru.iandreyshev.adobeKiller.presentation.viewModel.ICanvasViewModel
import ru.iandreyshev.canvas.presenter.ICanvasPresenter

interface IPresenterFactory {

    fun getCanvasAppModelPresenter(viewModel: ICanvasViewModel): ICanvasAppModel.IPresenter
    fun getCanvasPresenter(viewModel: ICanvasViewModel): ICanvasPresenter


}
