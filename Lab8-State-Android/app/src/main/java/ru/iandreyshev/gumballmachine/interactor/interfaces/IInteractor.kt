package ru.iandreyshev.gumballmachine.interactor.interfaces

import ru.iandreyshev.gumballmachine.useCase.interfaces.IUseCase

interface IInteractor<TUseCase : IUseCase<*>> {
    var useCase: TUseCase?
}
