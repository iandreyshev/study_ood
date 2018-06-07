package ru.iandreyshev.adobeKiller.di.factory

import ru.iandreyshev.adobeKiller.interactor.interfaces.ICanvasActivityInteractor
import ru.iandreyshev.adobeKiller.interactor.interfaces.IMenuActivityInteractor

interface IInteractorFactory {

    fun getCanvasActivityInteractor(): ICanvasActivityInteractor
    fun getMenuActivityInteractor(): IMenuActivityInteractor

}
