package ru.iandreyshev.compositeshapespaint.factory

import ru.iandreyshev.compositeshapespaint.interactor.MainInteractor
import ru.iandreyshev.compositeshapespaint.interactor.interfaces.IMainInteractor
import ru.iandreyshev.compositeshapespaint.useCase.interfaces.IMainUseCase
import ru.iandreyshev.gumballmachine.interactor.interfaces.IInteractorFactory
import ru.iandreyshev.gumballmachine.interactor.interfaces.IInteractor
import ru.iandreyshev.gumballmachine.useCase.interfaces.IUseCase
import kotlin.reflect.KClass

object InteractorFactory : IInteractorFactory {
    override fun <TInteractor : IInteractor>
            create(interactorClass: KClass<TInteractor>, useCase: IUseCase): IInteractor {
        return when (interactorClass) {
            IMainInteractor::class -> {
                MainInteractor(useCase as IMainUseCase)
            }
            else -> throw IllegalArgumentException("Unknown interactor class ${interactorClass.qualifiedName}")
        }
    }
}
