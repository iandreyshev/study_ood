package ru.iandreyshev.adobekiller.interactor.interfaces

import ru.iandreyshev.adobekiller.useCase.interfaces.IUseCase
import kotlin.reflect.KClass

interface IInteractorFactory {
    fun <TInteractor : IInteractor>
            create(interactorClass: KClass<TInteractor>, useCase: IUseCase): IInteractor
}
