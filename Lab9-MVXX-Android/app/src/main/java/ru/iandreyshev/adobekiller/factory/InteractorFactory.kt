package ru.iandreyshev.adobekiller.factory

import ru.iandreyshev.adobekiller.interactor.MainInteractor
import ru.iandreyshev.adobekiller.interactor.interfaces.IMainInteractor
import ru.iandreyshev.adobekiller.useCase.interfaces.IMainUseCase
import ru.iandreyshev.adobekiller.interactor.interfaces.IInteractorFactory
import ru.iandreyshev.adobekiller.interactor.interfaces.IInteractor
import ru.iandreyshev.adobekiller.useCase.interfaces.IUseCase
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
