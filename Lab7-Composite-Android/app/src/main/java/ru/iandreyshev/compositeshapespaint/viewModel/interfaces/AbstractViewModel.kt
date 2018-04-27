package ru.iandreyshev.compositeshapespaint.viewModel.interfaces

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import ru.iandreyshev.gumballmachine.viewModel.interfaces.IViewModel

abstract class AbstractViewModel<TInteractor>(
        app: Application
) : AndroidViewModel(app), IViewModel {
    var interactor: TInteractor? = null
}
