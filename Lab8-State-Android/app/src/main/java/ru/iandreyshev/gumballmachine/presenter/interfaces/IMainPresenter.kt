package ru.iandreyshev.gumballmachine.presenter.interfaces

import ru.iandreyshev.gumballmachine.machine.GumballMachineData
import ru.iandreyshev.gumballmachine.machine.GumballMachineError
import ru.iandreyshev.gumballmachine.viewModel.interfaces.IMachineViewModel

interface IMainPresenter : IPresenter {
    fun updateMachineData(data: GumballMachineData)

    fun onError(error: GumballMachineError)
}
