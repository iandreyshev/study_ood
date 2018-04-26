package ru.iandreyshev.gumballmachine.factory

import ru.iandreyshev.gumballmachine.presenter.interfaces.IPresenterFactory
import ru.iandreyshev.gumballmachine.presenter.MainPresenter
import ru.iandreyshev.gumballmachine.presenter.interfaces.IMainPresenter
import ru.iandreyshev.gumballmachine.presenter.interfaces.IPresenter
import ru.iandreyshev.gumballmachine.viewModel.interfaces.IMachineViewModel
import ru.iandreyshev.gumballmachine.viewModel.interfaces.AbstractViewModel
import kotlin.reflect.KClass

object PresenterFactory : IPresenterFactory {
    override fun <TPresenter : IPresenter>
            create(presenterClass: KClass<TPresenter>, viewModel: AbstractViewModel<*>?): IPresenter {
        return when (presenterClass) {
            IMainPresenter::class -> {
                MainPresenter(viewModel as IMachineViewModel)
            }
            else -> throw IllegalArgumentException("Unknown presenter class ${presenterClass.qualifiedName}")
        }
    }
}
