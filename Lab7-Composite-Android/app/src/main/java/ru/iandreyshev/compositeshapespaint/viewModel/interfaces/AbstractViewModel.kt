package ru.iandreyshev.compositeshapespaint.viewModel.interfaces

import android.arch.lifecycle.AndroidViewModel
import ru.iandreyshev.compositeshapespaint.app.ShapesApp
import ru.iandreyshev.gumballmachine.viewModel.interfaces.IViewModel

abstract class AbstractViewModel<TInteractor> : AndroidViewModel(
        ShapesApp.instance
), IViewModel {
    var interactor: TInteractor? = null
}
