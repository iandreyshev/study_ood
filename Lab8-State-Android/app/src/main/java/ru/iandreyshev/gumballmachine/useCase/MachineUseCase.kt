package ru.iandreyshev.gumballmachine.useCase

import ru.iandreyshev.gumballmachine.presenter.interfaces.IMachinePresenter
import ru.iandreyshev.gumballmachine.useCase.interfaces.IMachineUseCase

class MachineUseCase(override var presenter: IMachinePresenter?) : IMachineUseCase
