package ru.iandreyshev.gumballmachine.viewModel.interfaces

import android.app.Application
import android.arch.lifecycle.AndroidViewModel

abstract class AbstractViewModel<TInteractor>(app: Application) : AndroidViewModel(app) {
    var interactor: TInteractor? = null
}
