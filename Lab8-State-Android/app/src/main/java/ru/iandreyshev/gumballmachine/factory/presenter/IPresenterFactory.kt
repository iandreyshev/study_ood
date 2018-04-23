package ru.iandreyshev.gumballmachine.factory.presenter

import ru.iandreyshev.gumballmachine.presenter.AbstractPresenter
import ru.iandreyshev.gumballmachine.viewModel.BaseViewModel

interface IPresenterFactory<out TPresenter : AbstractPresenter<*>, in TViewModel: BaseViewModel<*>> {
    fun create(viewModel: TViewModel): TPresenter
}
