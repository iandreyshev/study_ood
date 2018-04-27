package ru.iandreyshev.gumballmachine.presenter

import ru.iandreyshev.gumballmachine.machine.GumballMachineData
import ru.iandreyshev.gumballmachine.machine.GumballMachineError
import ru.iandreyshev.gumballmachine.presenter.interfaces.IMainPresenter
import ru.iandreyshev.gumballmachine.viewModel.interfaces.IMachineViewModel

class MainPresenter(
        val viewModel: IMachineViewModel
) : IMainPresenter {
    override fun updateMachineData(data: GumballMachineData) {
        with(viewModel) {
            updateMachineName(data.name)
            updateStateName(data.stateName)
            updateBallsCount(data.ballsCount)
            updateInsertedCoinsCount(data.insertedCoinsCount)
            updateTotalCoinsCount(data.totalCoinsCount)
        }
    }

    override fun onError(error: GumballMachineError) =
            viewModel.onError(error.message)
}
