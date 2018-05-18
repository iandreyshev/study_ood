package ru.iandreyshev.adobeKiller.presentation.presenter.interfaces

import ru.iandreyshev.adobeKiller.app.UseCaseType
import ru.iandreyshev.adobeKiller.presentation.ui.viewModel.interfaces.InteractorViewModel
import kotlin.reflect.KClass

interface IPresenterFactory {

    fun create(useCaseType: UseCaseType, viewModel: InteractorViewModel<*>?): IPresenter

}
