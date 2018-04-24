package ru.iandreyshev.gumballmachine.factory.presenter

import ru.iandreyshev.gumballmachine.presenter.interfaces.IPresenter
import ru.iandreyshev.gumballmachine.viewModel.interfaces.AbstractViewModel
import kotlin.reflect.KClass

interface IPresenterFactory {
    fun <TPresenter : IPresenter<AbstractViewModel<*>>>
            create(presenterClass: KClass<TPresenter>, viewModel: AbstractViewModel<*>?): TPresenter
}
