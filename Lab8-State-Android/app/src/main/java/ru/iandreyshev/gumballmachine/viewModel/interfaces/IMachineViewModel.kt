package ru.iandreyshev.gumballmachine.viewModel.interfaces

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import ru.iandreyshev.gumballmachine.interactor.interfaces.IMachineInteractor

abstract class IMachineViewModel(
        app: Application
) : AbstractViewModel<IMachineInteractor>(app) {
    // OBSERVABLES
    abstract val totalQuartersCount: MutableLiveData<Int>
    abstract val insertedQuartersCount: MutableLiveData<Int>
    abstract val ballsCount: MutableLiveData<Int>
    // OBSERVABLES

    abstract var onErrorListener: ((String) -> Unit)

    abstract fun onError(error: String)
}
