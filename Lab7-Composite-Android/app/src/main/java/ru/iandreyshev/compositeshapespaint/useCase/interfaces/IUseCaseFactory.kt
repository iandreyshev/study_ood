package ru.iandreyshev.gumballmachine.useCase.interfaces

import ru.iandreyshev.gumballmachine.presenter.interfaces.IPresenter
import kotlin.reflect.KClass

interface IUseCaseFactory {
    fun <TUseCase : IUseCase>
            create(useCaseClass: KClass<TUseCase>, presenter: IPresenter): IUseCase
}
