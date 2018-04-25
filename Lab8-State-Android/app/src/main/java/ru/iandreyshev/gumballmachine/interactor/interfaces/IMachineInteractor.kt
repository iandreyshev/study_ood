package ru.iandreyshev.gumballmachine.interactor.interfaces

import ru.iandreyshev.gumballmachine.useCase.interfaces.IMachineUseCase

interface IMachineInteractor : IInteractor<IMachineUseCase> {
    fun insertCoin()

    fun turnCrank()

    fun removeCoin()

    fun fill(newBallsCount: Int)

    fun reset()
}
