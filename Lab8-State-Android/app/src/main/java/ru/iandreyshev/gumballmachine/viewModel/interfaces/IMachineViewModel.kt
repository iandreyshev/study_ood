package ru.iandreyshev.gumballmachine.viewModel.interfaces

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import ru.iandreyshev.gumballmachine.interactor.interfaces.IMachineInteractor

abstract class IMachineViewModel(
        app: Application
) : AbstractViewModel<IMachineInteractor>(app) {
    // OBSERVABLES
    val totalQuartersCount = MutableLiveData<Int>()
    val insertedQuartersCount = MutableLiveData<Int>()
    val ballsCount = MutableLiveData<Int>()
    // OBSERVABLES

    abstract fun onError(error: String)
}
