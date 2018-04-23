package ru.iandreyshev.gumballmachine.useCase.interfaces

import ru.iandreyshev.gumballmachine.presenter.interfaces.IPresenter

interface IUseCase<TPresenter : IPresenter<*>> {
    var presenter: TPresenter?
}
