package ru.iandreyshev.adobeKiller.di.factory

import ru.iandreyshev.adobeKiller.model.ICanvasAppModel
import ru.iandreyshev.canvas.presenter.ICanvasPresenter

interface IPresenterFactory {

    fun getCanvasPresenter(): ICanvasPresenter
    fun getCanvasAppModelPresenter(): ICanvasAppModel.IPresenter

}
