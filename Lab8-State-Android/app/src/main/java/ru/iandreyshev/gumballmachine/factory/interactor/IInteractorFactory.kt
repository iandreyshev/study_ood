package ru.iandreyshev.gumballmachine.factory.interactor

import ru.iandreyshev.gumballmachine.interactor.AbstractInteractor
import ru.iandreyshev.gumballmachine.useCase.IUseCase

interface IInteractorFactory<out TInteractor : AbstractInteractor<*>, in TUseCase : IUseCase> {
    fun create(useCase: TUseCase): TInteractor
}
