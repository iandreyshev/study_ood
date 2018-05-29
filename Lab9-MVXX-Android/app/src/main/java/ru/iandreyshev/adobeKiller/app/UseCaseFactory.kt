package ru.iandreyshev.adobeKiller.app

import ru.iandreyshev.adobeKiller.domain.adapter.CanvasStorageAdapter
import ru.iandreyshev.adobeKiller.domain.command.CommandQueue
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

    companion object {
        private const val COMMAND_QUEUE_SIZE = 12
    }

    override fun create(
            useCaseType: UseCaseType,
            presenter: IPresenter,
            data: Any?
    ): IUseCase = when (useCaseType) {
        UseCaseType.CANVAS -> {
            val commandQueue = CommandQueue(COMMAND_QUEUE_SIZE)
            val presentationModel = PresentationModel(
                    commandQueue = commandQueue)
            CanvasUseCase(
                    presenter = presenter as ICanvasPresenter,
                    presentationModel = presentationModel,
                    commandQueue = commandQueue,
                    localStorage = CanvasStorageAdapter(IUseCase.canvas.id, localStorage)
            )
        }

        UseCaseType.MENU -> MenuUseCase(
                presenter = presenter as IMenuPresenter,
                localStorage = localStorage
        )

    }

}
