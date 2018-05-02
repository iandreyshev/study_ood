package ru.iandreyshev.compositeshapespaint.ui.viewModel.interfaces

import android.arch.lifecycle.AndroidViewModel
import ru.iandreyshev.compositeshapespaint.app.ShapesApp
import ru.iandreyshev.compositeshapespaint.interactor.interfaces.IInteractor

abstract class AbstractViewModel<TInteractor : IInteractor> : AndroidViewModel(
        ShapesApp.instance
), IViewModel {

    var interactor: TInteractor? = null
}
