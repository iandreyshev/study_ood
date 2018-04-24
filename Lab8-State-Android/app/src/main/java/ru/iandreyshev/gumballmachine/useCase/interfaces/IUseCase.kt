package ru.iandreyshev.gumballmachine.useCase.interfaces

import ru.iandreyshev.gumballmachine.presenter.interfaces.IPresenter

interface IUseCase<out TPresenter : IPresenter<*>> {
    val presenter: TPresenter?
}
