package ru.iandreyshev.adobeKiller.app

import ru.iandreyshev.adobeKiller.domain.command.CommandQueue
import ru.iandreyshev.adobeKiller.domain.model.CanvasData
import ru.iandreyshev.adobeKiller.domain.presentationModel.PresentationModel
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.ICanvasPresenter
import ru.iandreyshev.adobeKiller.domain.useCase.CanvasUseCase
import ru.iandreyshev.adobeKiller.domain.useCase.MenuUseCase
import ru.iandreyshev.adobeKiller.domain.useCase.interfaces.IUseCaseFactory
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.IPresenter
import ru.iandreyshev.adobeKiller.domain.useCase.interfaces.IUseCase
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.IMenuPresenter
import ru.iandreyshev.localstorage.ILocalStorage

class UseCaseFactory(
        private val localStorage: ILocalStorage
) : IUseCaseFactory {

    override fun create(
            useCaseType: UseCaseType,
            presenter: IPresenter,
            data: Any?
    ): IUseCase = when (useCaseType) {

        UseCaseType.CANVAS -> {
            val commandQueue = CommandQueue(12)
            val presentationModel = PresentationModel(commandQueue)

            CanvasUseCase(
                    presenter = presenter as ICanvasPresenter,
                    presentationModel = presentationModel,
                    localStorage = localStorage,
                    canvas = CanvasData(id = 1, name = "Canvas 1")
            )
        }

        UseCaseType.MENU -> MenuUseCase(
                presenter = presenter as IMenuPresenter,
                localStorage = localStorage
        )

    }

}
