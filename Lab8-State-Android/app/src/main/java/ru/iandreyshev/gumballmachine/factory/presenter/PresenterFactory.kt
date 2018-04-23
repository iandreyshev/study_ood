package ru.iandreyshev.gumballmachine.factory.presenter

import ru.iandreyshev.gumballmachine.presenter.MachinePresenter
import ru.iandreyshev.gumballmachine.presenter.SettingsPresenter
import ru.iandreyshev.gumballmachine.presenter.interfaces.IMachinePresenter
import ru.iandreyshev.gumballmachine.presenter.interfaces.IPresenter
import ru.iandreyshev.gumballmachine.presenter.interfaces.ISettingsPresenter
import kotlin.reflect.KClass

object PresenterFactory : IPresenterFactory {
    override fun <TPresenter : IPresenter<*>> create(presenterClass: KClass<TPresenter>): TPresenter {
        return when (presenterClass) {
            IMachinePresenter::class -> MachinePresenter()
            ISettingsPresenter::class -> SettingsPresenter()
            else -> throw IllegalArgumentException("Unknown presenter class")
        } as TPresenter
    }
}
