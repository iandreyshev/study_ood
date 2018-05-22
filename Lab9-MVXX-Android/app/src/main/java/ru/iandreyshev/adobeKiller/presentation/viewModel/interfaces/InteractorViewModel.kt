package ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces

import android.arch.lifecycle.ViewModel
import ru.iandreyshev.adobeKiller.app.UseCaseType
import ru.iandreyshev.adobeKiller.presentation.interactor.interfaces.IInteractor

abstract class InteractorViewModel<TInteractor : IInteractor>(
        val useCaseType: UseCaseType
) : ViewModel(), IViewModel {

    var interactor: TInteractor? = null

}
