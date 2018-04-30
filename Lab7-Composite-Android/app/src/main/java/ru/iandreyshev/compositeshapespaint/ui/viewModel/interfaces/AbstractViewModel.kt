package ru.iandreyshev.compositeshapespaint.ui.viewModel.interfaces

import android.arch.lifecycle.AndroidViewModel
import ru.iandreyshev.compositeshapespaint.app.ShapesApp

abstract class AbstractViewModel<TInteractor> : AndroidViewModel(
        ShapesApp.instance
), IViewModel {
    var interactor: TInteractor? = null
}
