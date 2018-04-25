package ru.iandreyshev.gumballmachine.useCase.interfaces

interface IMachineUseCase : IUseCase {
    fun insertCoin()

    fun turnCrank()

    fun removeCoin()

    fun fill(newBallsCount: Int)

    fun reset()
}
