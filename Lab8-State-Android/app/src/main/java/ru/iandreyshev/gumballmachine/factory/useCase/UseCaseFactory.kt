package ru.iandreyshev.gumballmachine.factory.useCase

import ru.iandreyshev.gumballmachine.machine.GumballMachine
import ru.iandreyshev.gumballmachine.presenter.interfaces.IMachinePresenter
import ru.iandreyshev.gumballmachine.presenter.interfaces.IPresenter
import ru.iandreyshev.gumballmachine.presenter.interfaces.ISettingsPresenter
import ru.iandreyshev.gumballmachine.useCase.MachineUseCase
import ru.iandreyshev.gumballmachine.useCase.SettingsUseCase
import ru.iandreyshev.gumballmachine.useCase.interfaces.IMachineUseCase
import ru.iandreyshev.gumballmachine.useCase.interfaces.ISettingsUseCase
import ru.iandreyshev.gumballmachine.useCase.interfaces.IUseCase
import kotlin.reflect.KClass

object UseCaseFactory : IUseCaseFactory {
    override fun <TUseCase : IUseCase>
            create(useCaseClass: KClass<TUseCase>, presenter: IPresenter): IUseCase {
        return when(useCaseClass) {
            IMachineUseCase::class -> {
                MachineUseCase(
                        machine = GumballMachine(
                                startBallsCount = 10
                        ),
                        presenter = presenter as IMachinePresenter)
            }
            ISettingsUseCase::class -> {
                SettingsUseCase(presenter as ISettingsPresenter)
            }
            else -> throw IllegalArgumentException("Unknown use case class ${useCaseClass.qualifiedName}")
        }
    }
}
