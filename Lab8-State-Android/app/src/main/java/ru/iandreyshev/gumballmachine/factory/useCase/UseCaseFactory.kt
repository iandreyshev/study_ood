package ru.iandreyshev.gumballmachine.factory.useCase

import ru.iandreyshev.gumballmachine.useCase.interfaces.IMachineUseCase
import ru.iandreyshev.gumballmachine.useCase.interfaces.ISettingsUseCase
import ru.iandreyshev.gumballmachine.useCase.interfaces.IUseCase
import kotlin.reflect.KClass

object UseCaseFactory : IUseCaseFactory {
    override fun <TUseCase : IUseCase<*>> create(useCaseClass: KClass<TUseCase>): TUseCase {
        return when(useCaseClass) {
            IMachineUseCase::class -> {

            }
            ISettingsUseCase::class -> {

            }
            else -> throw IllegalArgumentException("Unknown use case class")
        } as TUseCase
    }
}
