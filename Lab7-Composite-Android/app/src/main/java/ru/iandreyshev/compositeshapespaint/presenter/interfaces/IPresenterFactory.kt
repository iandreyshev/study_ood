package ru.iandreyshev.gumballmachine.presenter.interfaces

import ru.iandreyshev.compositeshapespaint.viewModel.interfaces.AbstractViewModel
import kotlin.reflect.KClass

interface IPresenterFactory {
    fun <TPresenter : IPresenter>
            create(presenterClass: KClass<TPresenter>, viewModel: AbstractViewModel<*>?): IPresenter
}
