package ru.iandreyshev.gumballmachine.presenter.interfaces

import ru.iandreyshev.gumballmachine.viewModel.abstractions.AbstractViewModel

interface IPresenter<TViewModel : AbstractViewModel> {
    var viewModel: TViewModel?
}
