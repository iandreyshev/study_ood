package ru.iandreyshev.gumballmachine.presenter

import ru.iandreyshev.gumballmachine.presenter.interfaces.IMachinePresenter
import ru.iandreyshev.gumballmachine.viewModel.interfaces.IMachineViewModel

class MachinePresenter(override val viewModel: IMachineViewModel?) : IMachinePresenter
