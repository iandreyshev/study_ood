package ru.iandreyshev.adobeKiller.presentation.interactor.interfaces

import ru.iandreyshev.adobeKiller.app.UseCaseType
import ru.iandreyshev.adobeKiller.domain.useCase.interfaces.IUseCase
import kotlin.reflect.KClass

interface IInteractorFactory {
    fun create(useCaseType: UseCaseType, useCase: IUseCase): IInteractor
}
