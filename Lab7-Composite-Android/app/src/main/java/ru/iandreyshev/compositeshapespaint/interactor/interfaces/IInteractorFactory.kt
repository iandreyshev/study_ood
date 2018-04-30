package ru.iandreyshev.compositeshapespaint.interactor.interfaces

import ru.iandreyshev.compositeshapespaint.useCase.interfaces.IUseCase
import kotlin.reflect.KClass

interface IInteractorFactory {
    fun <TInteractor : IInteractor>
            create(interactorClass: KClass<TInteractor>, useCase: IUseCase): IInteractor
}
