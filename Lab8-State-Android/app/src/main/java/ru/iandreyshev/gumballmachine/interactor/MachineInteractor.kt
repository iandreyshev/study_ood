package ru.iandreyshev.gumballmachine.interactor

import ru.iandreyshev.gumballmachine.interactor.interfaces.IMachineInteractor
import ru.iandreyshev.gumballmachine.useCase.interfaces.IMachineUseCase

class MachineInteractor(override val useCase: IMachineUseCase?) : IMachineInteractor
