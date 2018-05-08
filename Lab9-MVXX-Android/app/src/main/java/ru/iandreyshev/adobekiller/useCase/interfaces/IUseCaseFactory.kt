package ru.iandreyshev.adobekiller.useCase.interfaces

import ru.iandreyshev.adobekiller.presenter.interfaces.IPresenter
import kotlin.reflect.KClass

interface IUseCaseFactory {
    fun <TUseCase : IUseCase>
            create(useCaseClass: KClass<TUseCase>, presenter: IPresenter): IUseCase
}
