package ru.iandreyshev.adobeKiller.presentation.presenter.interfaces

import ru.iandreyshev.adobeKiller.app.ViewControllerType
import ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces.ControllerViewModel

interface IPresenterFactory {

    fun create(viewControllerType: ViewControllerType, viewModel: ControllerViewModel<*>?): IPresenter

}
