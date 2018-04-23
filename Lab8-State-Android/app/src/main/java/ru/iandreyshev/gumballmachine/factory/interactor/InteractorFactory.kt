package ru.iandreyshev.gumballmachine.factory.interactor

import ru.iandreyshev.gumballmachine.interactor.MachineInteractor
import ru.iandreyshev.gumballmachine.interactor.SettingsInteractor
import ru.iandreyshev.gumballmachine.interactor.interfaces.IInteractor
import kotlin.reflect.KClass

object InteractorFactory : IInteractorFactory {
    override fun <TInteractor : IInteractor<*>>
            create(interactorClass: KClass<TInteractor>): TInteractor {
        return when (interactorClass) {
            MachineInteractor::class -> {
                MachineInteractor()
            }
            SettingsInteractor::class -> {
                SettingsInteractor()
            }
            else -> throw IllegalArgumentException("Unknown interactor class")
        } as TInteractor
    }
}
