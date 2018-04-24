package ru.iandreyshev.gumballmachine.viewModel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import ru.iandreyshev.gumballmachine.viewModel.interfaces.IMachineViewModel

class MachineViewModel(app: Application) : IMachineViewModel(app) {
    override val totalQuartersCount: MutableLiveData<Int> = MutableLiveData()
    override val insertedQuartersCount: MutableLiveData<Int> = MutableLiveData()
    override val ballsCount: MutableLiveData<Int> = MutableLiveData()

    override var onErrorListener: ((String) -> Unit) = {}

    override fun onError(error: String) = onErrorListener(error)
}
