package ru.iandreyshev.gumballmachine.interactor.interfaces

import ru.iandreyshev.gumballmachine.useCase.interfaces.IUseCase

interface IInteractor<out TUseCase : IUseCase<*>> {
    val useCase: TUseCase?
}
