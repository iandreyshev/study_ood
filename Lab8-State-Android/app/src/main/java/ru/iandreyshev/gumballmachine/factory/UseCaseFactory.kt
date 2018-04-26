package ru.iandreyshev.gumballmachine.factory

import ru.iandreyshev.gumballmachine.machine.EnumUsingGumballMachine
import ru.iandreyshev.gumballmachine.useCase.interfaces.IUseCaseFactory
import ru.iandreyshev.gumballmachine.machine.StateUsingGumballMachine
import ru.iandreyshev.gumballmachine.presenter.interfaces.IMainPresenter
import ru.iandreyshev.gumballmachine.presenter.interfaces.IPresenter
import ru.iandreyshev.gumballmachine.useCase.MainUseCase
import ru.iandreyshev.gumballmachine.useCase.interfaces.IMainUseCase
import ru.iandreyshev.gumballmachine.useCase.interfaces.IUseCase
import kotlin.reflect.KClass

object UseCaseFactory : IUseCaseFactory {
    override fun <TUseCase : IUseCase>
            create(useCaseClass: KClass<TUseCase>, presenter: IPresenter): IUseCase {
        return when(useCaseClass) {
            IMainUseCase::class -> {
                MainUseCase(
                        machineWithState = StateUsingGumballMachine(72),
                        machineWithEnums = EnumUsingGumballMachine(42),
                        presenter = presenter as IMainPresenter)
            }
            else -> throw IllegalArgumentException("Unknown use case class ${useCaseClass.qualifiedName}")
        }
    }
}
