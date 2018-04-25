package ru.iandreyshev.gumballmachine.viewModel.interfaces

import android.app.Application
import android.arch.lifecycle.AndroidViewModel

abstract class AbstractViewModel<TInteractor>(
        app: Application
) : AndroidViewModel(app), IViewModel {
    var interactor: TInteractor? = null
}
