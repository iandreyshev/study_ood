package ru.iandreyshev.gumballmachine.presenter.interfaces

import ru.iandreyshev.gumballmachine.machine.GumballMachineData
import ru.iandreyshev.gumballmachine.machine.GumballMachineError
import ru.iandreyshev.gumballmachine.viewModel.interfaces.IMachineViewModel

interface IMachinePresenter : IPresenter {
    fun updateMachineData(newData: GumballMachineData)

    fun onError(error: GumballMachineError)
}
