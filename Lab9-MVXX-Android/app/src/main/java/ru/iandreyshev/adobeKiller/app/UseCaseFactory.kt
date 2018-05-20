package ru.iandreyshev.adobeKiller.app

import ru.iandreyshev.adobeKiller.presentation.drawing.factory.ConcreteShapeFactory
import ru.iandreyshev.adobeKiller.data.IRepository
import ru.iandreyshev.adobeKiller.domain.model.CanvasData
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.ICanvasPresenter
import ru.iandreyshev.adobeKiller.domain.useCase.CanvasUseCase
import ru.iandreyshev.adobeKiller.domain.useCase.MenuUseCase
import ru.iandreyshev.adobeKiller.domain.useCase.interfaces.IUseCaseFactory
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.IPresenter
import ru.iandreyshev.adobeKiller.domain.useCase.interfaces.IUseCase
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.IMenuPresenter

internal class UseCaseFactory(
        private val repository: IRepository
) : IUseCaseFactory {

    override fun create(
            useCaseType: UseCaseType,
            presenter: IPresenter,
            data: Any?
    ): IUseCase = when (useCaseType) {

        UseCaseType.CANVAS -> CanvasUseCase(
                presenter = presenter as ICanvasPresenter,
                shapesFactory = ConcreteShapeFactory,
                canvas = CanvasData(id = 1, name = "Canvas 1")
        )

        UseCaseType.MENU -> MenuUseCase(
                presenter = presenter as IMenuPresenter,
                repository = repository
        )

    }

}
