package ru.iandreyshev.adobeKiller.domain.useCase.interfaces

import ru.iandreyshev.adobeKiller.app.UseCaseType
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.IPresenter

interface IUseCaseFactory {

    fun create(useCaseType: UseCaseType, presenter: IPresenter, data: Any?): IUseCase

}
