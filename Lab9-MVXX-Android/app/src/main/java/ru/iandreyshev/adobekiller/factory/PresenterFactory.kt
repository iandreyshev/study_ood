package ru.iandreyshev.adobekiller.factory

import ru.iandreyshev.adobekiller.presenter.MainPresenter
import ru.iandreyshev.adobekiller.presenter.interfaces.IMainPresenter
import ru.iandreyshev.adobekiller.ui.viewModel.interfaces.AbstractViewModel
import ru.iandreyshev.adobekiller.ui.viewModel.interfaces.IMainViewModel
import ru.iandreyshev.adobekiller.presenter.interfaces.IPresenterFactory
import ru.iandreyshev.adobekiller.presenter.interfaces.IPresenter
import kotlin.reflect.KClass

object PresenterFactory : IPresenterFactory {
    override fun <TPresenter : IPresenter>
            create(presenterClass: KClass<TPresenter>, viewModel: AbstractViewModel<*>?): IPresenter {
        return when (presenterClass) {
            IMainPresenter::class -> {
                MainPresenter(viewModel as IMainViewModel)
            }
            else -> throw IllegalArgumentException("Unknown presenter class ${presenterClass.qualifiedName}")
        }
    }
}
