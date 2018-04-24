package ru.iandreyshev.gumballmachine.factory.interactor

import ru.iandreyshev.gumballmachine.interactor.interfaces.IInteractor
import ru.iandreyshev.gumballmachine.useCase.interfaces.IUseCase
import kotlin.reflect.KClass

interface IInteractorFactory {
    fun<TInteractor : IInteractor<*>>
            create(interactorClass: KClass<TInteractor>, useCase: IUseCase<*>): TInteractor
}
