package ru.iandreyshev.adobeKiller.app

import ru.iandreyshev.adobeKiller.presentation.presenter.CanvasPresenter
import ru.iandreyshev.adobeKiller.presentation.presenter.MenuPresenter
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.IPresenter
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.IPresenterFactory
import ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces.ICanvasViewModel
import ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces.IMenuViewModel
import ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces.ControllerViewModel

class PresenterFactory : IPresenterFactory {

    override fun create(
            viewControllerType: ViewControllerType,
            viewModel: ControllerViewModel<*>?): IPresenter = when (viewControllerType) {

        ViewControllerType.MENU -> MenuPresenter(
                viewModel = viewModel as IMenuViewModel)

        ViewControllerType.CANVAS -> CanvasPresenter(
                viewModel = viewModel as ICanvasViewModel)

    }

}
