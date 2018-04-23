package ru.iandreyshev.gumballmachine.factory.presenter

import ru.iandreyshev.gumballmachine.presenter.interfaces.IPresenter
import kotlin.reflect.KClass

interface IPresenterFactory {
    fun <TPresenter : IPresenter<*>> create(presenterClass: KClass<TPresenter>): TPresenter
}
