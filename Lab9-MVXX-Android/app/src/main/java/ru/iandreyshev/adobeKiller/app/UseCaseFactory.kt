package ru.iandreyshev.adobeKiller.app

import ru.iandreyshev.adobeKiller.domain.adapter.CanvasStorageAdapter
import ru.iandreyshev.adobeKiller.domain.command.ICommandQueue
import ru.iandreyshev.adobeKiller.domain.presentationModel.IPresentationModel
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.ICanvasPresenter
import ru.iandreyshev.adobeKiller.domain.useCase.CanvasUseCase
import ru.iandreyshev.adobeKiller.domain.useCase.MenuUseCase
import ru.iandreyshev.adobeKiller.domain.useCase.interfaces.IUseCaseFactory
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.IPresenter
import ru.iandreyshev.adobeKiller.domain.useCase.interfaces.IUseCase
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.IMenuPresenter

class UseCaseFactory(
        private val presentationModel: IPresentationModel,
        private val commandQueue: ICommandQueue,
        private val storageAdapter: CanvasStorageAdapter
) : IUseCaseFactory {

    override fun create(
            useCaseType: UseCaseType,
            presenter: IPresenter,
            data: Any?
    ): IUseCase = when (useCaseType) {
        UseCaseType.CANVAS -> {
            CanvasUseCase(
                    presenter = presenter as ICanvasPresenter,
                    presentationModel = presentationModel,
                    commandQueue = commandQueue,
                    canvasSerializer = storageAdapter
            )
        }

        UseCaseType.MENU -> MenuUseCase(
                presenter = presenter as IMenuPresenter,
                localStorage = storageAdapter
        )

    }

}
