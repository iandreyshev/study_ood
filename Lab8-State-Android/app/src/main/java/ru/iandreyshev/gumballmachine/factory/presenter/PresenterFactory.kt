package ru.iandreyshev.gumballmachine.factory.presenter

import ru.iandreyshev.gumballmachine.presenter.MachinePresenter
import ru.iandreyshev.gumballmachine.presenter.SettingsPresenter
import ru.iandreyshev.gumballmachine.presenter.interfaces.IMachinePresenter
import ru.iandreyshev.gumballmachine.presenter.interfaces.IPresenter
import ru.iandreyshev.gumballmachine.presenter.interfaces.ISettingsPresenter
import ru.iandreyshev.gumballmachine.viewModel.interfaces.IMachineViewModel
import ru.iandreyshev.gumballmachine.viewModel.interfaces.ISettingsViewModel
import ru.iandreyshev.gumballmachine.viewModel.interfaces.AbstractViewModel
import kotlin.reflect.KClass

object PresenterFactory : IPresenterFactory {
    override fun <TPresenter : IPresenter<*>>
            create(presenterClass: KClass<TPresenter>, viewModel: AbstractViewModel<*>?): TPresenter {
        return when (presenterClass) {
            IMachinePresenter::class -> {
                MachinePresenter(viewModel as IMachineViewModel)
            }
            ISettingsPresenter::class -> {
                SettingsPresenter(viewModel as ISettingsViewModel)
            }
            else -> throw IllegalArgumentException("Unknown presenter class")
        } as TPresenter
    }
}
