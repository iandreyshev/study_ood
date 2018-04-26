package ru.iandreyshev.gumballmachine.viewModel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import ru.iandreyshev.gumballmachine.interactor.interfaces.IMachineInteractor
import ru.iandreyshev.gumballmachine.viewModel.interfaces.AbstractViewModel
import ru.iandreyshev.gumballmachine.viewModel.interfaces.IMachineViewModel

class MachineViewModel(app: Application) : AbstractViewModel<IMachineInteractor>(app), IMachineViewModel {

    // OBSERVABLES
    val totalCoinsCount: MutableLiveData<Int> = MutableLiveData()
    val insertedCoinsCount: MutableLiveData<Int> = MutableLiveData()
    val ballsCount: MutableLiveData<Int> = MutableLiveData()
    var onErrorListener: ((String) -> Unit) = {}
    // OBSERVABLES

    override fun updateBallsCount(newCount: Int) =
            ballsCount.postValue(newCount)

    override fun updateInsertedCoinsCount(newCount: Int) =
            insertedCoinsCount.postValue(newCount)

    override fun updateTotalCoinsCount(newCount: Int) =
            totalCoinsCount.postValue(newCount)

    override fun onError(error: String) =
            onErrorListener(error)
}
