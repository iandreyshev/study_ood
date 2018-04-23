package ru.iandreyshev.gumballmachine.presenter

import ru.iandreyshev.gumballmachine.viewModel.BaseViewModel

abstract class AbstractPresenter<TViewModel : BaseViewModel<*>> {
    var viewModel: TViewModel? = null
}
