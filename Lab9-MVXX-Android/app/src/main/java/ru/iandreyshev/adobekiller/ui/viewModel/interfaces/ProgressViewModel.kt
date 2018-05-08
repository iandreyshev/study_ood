package ru.iandreyshev.adobekiller.ui.viewModel.interfaces

import android.arch.lifecycle.MutableLiveData
import ru.iandreyshev.adobekiller.interactor.interfaces.IInteractor

abstract class ProgressViewModel<TInteractor : IInteractor> : AbstractViewModel<TInteractor>(), IProgressViewModel {

    // OBSERVABLES
    val showProgress = MutableLiveData<Boolean>()
    // OBSERVABLES

    override fun startProgress(isStart: Boolean) =
            showProgress.postValue(isStart)
}
