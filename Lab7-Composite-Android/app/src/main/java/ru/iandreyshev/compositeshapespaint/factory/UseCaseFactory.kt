package ru.iandreyshev.compositeshapespaint.factory

import ru.iandreyshev.compositeshapespaint.presenter.interfaces.IMainPresenter
import ru.iandreyshev.compositeshapespaint.useCase.MainUseCase
import ru.iandreyshev.compositeshapespaint.useCase.interfaces.IMainUseCase
import ru.iandreyshev.gumballmachine.useCase.interfaces.IUseCaseFactory
import ru.iandreyshev.gumballmachine.presenter.interfaces.IPresenter
import ru.iandreyshev.gumballmachine.useCase.interfaces.IUseCase
import kotlin.reflect.KClass

object UseCaseFactory : IUseCaseFactory {
    override fun <TUseCase : IUseCase>
            create(useCaseClass: KClass<TUseCase>, presenter: IPresenter): IUseCase {
        return when(useCaseClass) {
            IMainUseCase::class -> {
                MainUseCase(presenter as IMainPresenter)
            }
            else -> throw IllegalArgumentException("Unknown use case class ${useCaseClass.qualifiedName}")
        }
    }
}
