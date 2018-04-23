package ru.iandreyshev.gumballmachine.factory.useCase

import ru.iandreyshev.gumballmachine.presenter.AbstractPresenter
import ru.iandreyshev.gumballmachine.useCase.IUseCase

interface IUseCaseFactory<out TUseCase : IUseCase, in TPresenter : AbstractPresenter<*>> {
    fun create(presenter: TPresenter): TUseCase
}
