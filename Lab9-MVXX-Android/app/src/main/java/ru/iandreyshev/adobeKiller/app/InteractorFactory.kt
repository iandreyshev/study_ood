package ru.iandreyshev.adobeKiller.app

import ru.iandreyshev.adobeKiller.presentation.interactor.CanvasInteractor
import ru.iandreyshev.adobeKiller.domain.useCase.interfaces.ICanvasUseCase
import ru.iandreyshev.adobeKiller.domain.useCase.interfaces.IMenuUseCase
import ru.iandreyshev.adobeKiller.presentation.interactor.interfaces.IInteractorFactory
import ru.iandreyshev.adobeKiller.presentation.interactor.interfaces.IInteractor
import ru.iandreyshev.adobeKiller.domain.useCase.interfaces.IUseCase
import ru.iandreyshev.adobeKiller.presentation.interactor.MenuInteractor

class InteractorFactory : IInteractorFactory {

    override fun create(useCaseType: UseCaseType, useCase: IUseCase): IInteractor = when (useCaseType) {
        UseCaseType.MENU -> MenuInteractor(useCase as IMenuUseCase)
        UseCaseType.CANVAS -> CanvasInteractor(useCase as ICanvasUseCase)
    }

}
