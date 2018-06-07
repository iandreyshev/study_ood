package ru.iandreyshev.adobeKiller.di.factory

import ru.iandreyshev.adobeKiller.interactor.CanvasActivityInteractor

interface IInteractorFactory {

    fun getCanvasActivityInteractor(): CanvasActivityInteractor

}
