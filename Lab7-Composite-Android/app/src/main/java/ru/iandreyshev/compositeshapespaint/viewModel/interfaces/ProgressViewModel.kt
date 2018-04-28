package ru.iandreyshev.compositeshapespaint.viewModel.interfaces

import android.arch.lifecycle.MutableLiveData

open class ProgressViewModel<T> : AbstractViewModel<T>(), IProgressViewModel {

    // OBSERVABLES
    val showProgress = MutableLiveData<Boolean>()
    // OBSERVABLES

    override fun startProgress(isStart: Boolean) =
            showProgress.postValue(isStart)
}
