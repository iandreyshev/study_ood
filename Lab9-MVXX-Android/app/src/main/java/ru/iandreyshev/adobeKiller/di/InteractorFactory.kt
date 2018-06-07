package ru.iandreyshev.adobeKiller.di

import ru.iandreyshev.adobeKiller.di.factory.IInteractorFactory
import ru.iandreyshev.adobeKiller.interactor.CanvasActivityInteractor
import ru.iandreyshev.adobeKiller.interactor.MenuActivityInteractor
import ru.iandreyshev.adobeKiller.interactor.interfaces.ICanvasActivityInteractor
import ru.iandreyshev.adobeKiller.interactor.interfaces.IMenuActivityInteractor
import ru.iandreyshev.adobeKiller.model.ApplicationModel

class InteractorFactory(
        private val applicationModel: ApplicationModel
) : IInteractorFactory {

    override fun getCanvasActivityInteractor(): ICanvasActivityInteractor {
        return CanvasActivityInteractor(applicationModel)
    }

    override fun getMenuActivityInteractor(): IMenuActivityInteractor {
        return MenuActivityInteractor()
    }

}
