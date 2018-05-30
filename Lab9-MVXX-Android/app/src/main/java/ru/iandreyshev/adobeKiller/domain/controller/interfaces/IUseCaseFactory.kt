package ru.iandreyshev.adobeKiller.domain.controller.interfaces

import ru.iandreyshev.adobeKiller.app.ViewControllerType
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.IPresenter

interface IUseCaseFactory {

    fun create(viewControllerType: ViewControllerType, presenter: IPresenter, data: Any?): IViewController

}
