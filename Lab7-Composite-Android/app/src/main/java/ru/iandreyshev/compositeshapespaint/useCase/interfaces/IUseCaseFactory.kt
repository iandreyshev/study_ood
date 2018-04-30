package ru.iandreyshev.compositeshapespaint.useCase.interfaces

import ru.iandreyshev.compositeshapespaint.presenter.interfaces.IPresenter
import kotlin.reflect.KClass

interface IUseCaseFactory {
    fun <TUseCase : IUseCase>
            create(useCaseClass: KClass<TUseCase>, presenter: IPresenter): IUseCase
}
