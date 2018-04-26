package ru.iandreyshev.gumballmachine.factory

import ru.iandreyshev.gumballmachine.useCase.interfaces.IUseCaseFactory
import ru.iandreyshev.gumballmachine.machine.GumballMachineWithStatePattern
import ru.iandreyshev.gumballmachine.machine.IGumballMachine
import ru.iandreyshev.gumballmachine.machine.IGumballMachineFactory
import ru.iandreyshev.gumballmachine.presenter.interfaces.IMachinePresenter
import ru.iandreyshev.gumballmachine.presenter.interfaces.IPresenter
import ru.iandreyshev.gumballmachine.presenter.interfaces.ISettingsPresenter
import ru.iandreyshev.gumballmachine.useCase.MachineUseCase
import ru.iandreyshev.gumballmachine.useCase.SettingsUseCase
import ru.iandreyshev.gumballmachine.useCase.interfaces.IMachineUseCase
import ru.iandreyshev.gumballmachine.useCase.interfaces.ISettingsUseCase
import ru.iandreyshev.gumballmachine.useCase.interfaces.IUseCase
import kotlin.reflect.KClass

class UseCaseFactory<TMachine : IGumballMachine>(
        private val machineFactory: IGumballMachineFactory
) : IUseCaseFactory {
    override fun <TUseCase : IUseCase>
            create(useCaseClass: KClass<TUseCase>, presenter: IPresenter): IUseCase {
        return when (useCaseClass) {
            IMachineUseCase::class -> {
                MachineUseCase(
                        machine = machineFactory.create(machineFactory),
                        presenter = presenter as IMachinePresenter)
            }
            ISettingsUseCase::class -> {
                SettingsUseCase(presenter as ISettingsPresenter)
            }
            else -> throw IllegalArgumentException("Unknown use case class ${useCaseClass.qualifiedName}")
        }
    }

    private fun createMachine(): IGumballMachine {
        return when (machineClass) {
            GumballMachineWithStatePattern::class -> {
                GumballMachineWithStatePattern(startBallsCount = 10)
            }
            else -> throw IllegalArgumentException("Invalid machine class ${machineClass.qualifiedName}")
        }
    }
}
