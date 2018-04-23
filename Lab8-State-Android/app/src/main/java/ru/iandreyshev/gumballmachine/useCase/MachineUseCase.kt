package ru.iandreyshev.gumballmachine.useCase

import ru.iandreyshev.gumballmachine.presenter.MachinePresenter
import ru.iandreyshev.gumballmachine.useCase.interfaces.IMachineUseCase

class MachineUseCase(private val presenter: MachinePresenter) : IMachineUseCase
