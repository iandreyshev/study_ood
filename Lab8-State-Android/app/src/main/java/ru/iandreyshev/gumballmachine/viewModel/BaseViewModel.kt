package ru.iandreyshev.gumballmachine.viewModel

import android.arch.lifecycle.ViewModel
import ru.iandreyshev.gumballmachine.interactor.AbstractInteractor

abstract class BaseViewModel<TInteractor : AbstractInteractor<*>> : ViewModel() {
    var interactor: TInteractor? = null
}
