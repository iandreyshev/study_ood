package ru.iandreyshev.adobeKiller.presentation.presenter.interfaces

import ru.iandreyshev.adobeKiller.app.UseCaseType
import ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces.InteractorViewModel

interface IPresenterFactory {

    fun create(useCaseType: UseCaseType, viewModel: InteractorViewModel<*>?): IPresenter

}
