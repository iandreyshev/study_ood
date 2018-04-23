package ru.iandreyshev.gumballmachine.interactor

import ru.iandreyshev.gumballmachine.useCase.IUseCase

abstract class AbstractInteractor<TUseCase : IUseCase> {
    var useCase: TUseCase? = null
}
