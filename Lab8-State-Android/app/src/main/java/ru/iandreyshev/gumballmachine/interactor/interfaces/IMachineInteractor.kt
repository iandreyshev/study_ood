package ru.iandreyshev.gumballmachine.interactor.interfaces

import ru.iandreyshev.gumballmachine.useCase.interfaces.IMachineUseCase

interface IMachineInteractor : IInteractor<IMachineUseCase> {
    fun insertQuarter()

    fun turnCrank()

    fun removeQuarter()

    fun reset()
}
