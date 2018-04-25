package ru.iandreyshev.gumballmachine.presenter

import ru.iandreyshev.gumballmachine.machine.GumballMachineData
import ru.iandreyshev.gumballmachine.machine.GumballMachineError
import ru.iandreyshev.gumballmachine.presenter.interfaces.IMachinePresenter
import ru.iandreyshev.gumballmachine.viewModel.interfaces.IMachineViewModel

class MachinePresenter(override val viewModel: IMachineViewModel) : IMachinePresenter {
    override fun updateMachineData(newData: GumballMachineData) {
        with(viewModel) {
            ballsCount.postValue(newData.ballsCount)
            insertedCoinsCount.postValue(newData.insertedCoinsCount)
            totalCoinsCount.postValue(newData.totalCoinsCount)
        }
    }

    override fun onError(error: GumballMachineError) =
            viewModel.onError(error.message)
}
