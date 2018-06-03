package ru.iandreyshev.adobeKiller.app

import ru.iandreyshev.adobeKiller.domain.adapter.CanvasStorageAdapter
import ru.iandreyshev.adobeKiller.domain.command.CommandQueue
import ru.iandreyshev.adobeKiller.domain.canvasEngine.CanvasEngine
import ru.iandreyshev.adobeKiller.domain.controller.CanvasViewController
import ru.iandreyshev.adobeKiller.domain.controller.MenuViewController
import ru.iandreyshev.adobeKiller.domain.controller.interfaces.IViewController
import ru.iandreyshev.adobeKiller.domain.controller.interfaces.IUseCaseFactory
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.ICanvasPresenter
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.IMenuPresenter
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.IPresenter
import ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces.IViewModel
import ru.iandreyshev.localstorage.ILocalStorage

class ViewControllerFactory(
        private val localStorage: ILocalStorage
) : IUseCaseFactory {

    companion object {
        private const val COMMAND_QUEUE_SIZE = 12
    }

    override fun create(
            viewControllerType: ViewControllerType,
            presenter: IPresenter,
            data: Any?
    ): IViewController = when (viewControllerType) {
        ViewControllerType.CANVAS -> {
            val commandQueue = CommandQueue(COMMAND_QUEUE_SIZE)
            val presentationModel = CanvasEngine(commandQueue)
            val storage = CanvasStorageAdapter(localStorage, IViewController.canvas.id)

            CanvasViewController(
                    presenter = presenter as ICanvasPresenter,
                    canvasEngine = presentationModel,
                    commandQueue = commandQueue,
                    canvasSerializer = storage
            )
        }

        ViewControllerType.MENU -> MenuViewController(
                presenter = presenter as IMenuPresenter,
                localStorage = localStorage
        )

    }

}
