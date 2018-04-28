package ru.iandreyshev.compositeshapespaint.ui.viewModel.interfaces

import android.arch.lifecycle.MutableLiveData

abstract class ProgressViewModel<TInteractor> : AbstractViewModel<TInteractor>(), IProgressViewModel {

    // OBSERVABLES
    val showProgress = MutableLiveData<Boolean>()
    // OBSERVABLES

    override fun startProgress(isStart: Boolean) =
            showProgress.postValue(isStart)
}
