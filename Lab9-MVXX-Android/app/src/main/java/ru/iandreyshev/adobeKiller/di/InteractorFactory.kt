package ru.iandreyshev.adobeKiller.di

import ru.iandreyshev.adobeKiller.di.factory.IInteractorFactory
import ru.iandreyshev.adobeKiller.interactor.CanvasActivityInteractor
import ru.iandreyshev.adobeKiller.model.CanvasApplicationModel

class InteractorFactory(
        private val applicationModel: CanvasApplicationModel
) : IInteractorFactory {

    override fun getCanvasActivityInteractor(): CanvasActivityInteractor {
        return CanvasActivityInteractor(applicationModel)
    }

}
