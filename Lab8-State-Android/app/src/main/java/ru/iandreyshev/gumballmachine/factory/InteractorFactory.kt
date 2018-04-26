package ru.iandreyshev.gumballmachine.factory

import ru.iandreyshev.gumballmachine.interactor.MachineInteractor
import ru.iandreyshev.gumballmachine.interactor.SettingsInteractor
import ru.iandreyshev.gumballmachine.interactor.interfaces.IInteractor
import ru.iandreyshev.gumballmachine.interactor.interfaces.IInteractorFactory
import ru.iandreyshev.gumballmachine.interactor.interfaces.IMachineInteractor
import ru.iandreyshev.gumballmachine.interactor.interfaces.ISettingsInteractor
import ru.iandreyshev.gumballmachine.useCase.interfaces.IMachineUseCase
import ru.iandreyshev.gumballmachine.useCase.interfaces.ISettingsUseCase
import ru.iandreyshev.gumballmachine.useCase.interfaces.IUseCase
import kotlin.reflect.KClass

object InteractorFactory : IInteractorFactory {
    override fun <TInteractor : IInteractor>
            create(interactorClass: KClass<TInteractor>, useCase: IUseCase): IInteractor {
        return when (interactorClass) {
            IMachineInteractor::class -> {
                MachineInteractor(useCase as IMachineUseCase)
            }
            ISettingsInteractor::class -> {
                SettingsInteractor(useCase as ISettingsUseCase)
            }
            else -> throw IllegalArgumentException("Unknown interactor class ${interactorClass.qualifiedName}")
        }
    }
}
