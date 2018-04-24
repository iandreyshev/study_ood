package ru.iandreyshev.gumballmachine.presenter.interfaces

import ru.iandreyshev.gumballmachine.viewModel.interfaces.AbstractViewModel

interface IPresenter<out TViewModel : AbstractViewModel<*>> {
    val viewModel: TViewModel?
}
