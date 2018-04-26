package ru.iandreyshev.gumballmachine.interactor.interfaces

import ru.iandreyshev.gumballmachine.useCase.interfaces.IUseCase
import kotlin.reflect.KClass

interface IInteractorFactory {
    fun <TInteractor : IInteractor>
            create(interactorClass: KClass<TInteractor>, useCase: IUseCase): IInteractor
}
