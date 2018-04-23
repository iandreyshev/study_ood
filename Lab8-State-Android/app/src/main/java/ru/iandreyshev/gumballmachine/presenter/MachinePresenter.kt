package ru.iandreyshev.gumballmachine.presenter

import ru.iandreyshev.gumballmachine.presenter.interfaces.IMachinePresenter
import ru.iandreyshev.gumballmachine.viewModel.abstractions.AbstractMachineViewModel

class MachinePresenter : IMachinePresenter {
    override var viewModel: AbstractMachineViewModel? = null
}
