package ru.iandreyshev.gumballmachine.viewModel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import ru.iandreyshev.gumballmachine.interactor.interfaces.IMainInteractor
import ru.iandreyshev.gumballmachine.viewModel.interfaces.AbstractViewModel
import ru.iandreyshev.gumballmachine.viewModel.interfaces.IMachineViewModel

class MainViewModel(
        app: Application
) : AbstractViewModel<IMainInteractor>(app), IMachineViewModel {

    // OBSERVABLES
    val machineName = MutableLiveData<String>()
    val stateName = MutableLiveData<String>()
    val totalCoinsCount = MutableLiveData<Int>()
    val insertedCoinsCount = MutableLiveData<Int>()
    val ballsCount = MutableLiveData<Int>()
    var onErrorListener: ((String) -> Unit) = {}
    // OBSERVABLES

    override fun updateMachineName(name: String) =
            machineName.postValue(name)

    override fun updateStateName(name: String) =
            stateName.postValue(name)

    override fun updateBallsCount(newCount: Int) =
            ballsCount.postValue(newCount)

    override fun updateInsertedCoinsCount(newCount: Int) =
            insertedCoinsCount.postValue(newCount)

    override fun updateTotalCoinsCount(newCount: Int) =
            totalCoinsCount.postValue(newCount)

    override fun onError(error: String) =
            onErrorListener(error)
}
