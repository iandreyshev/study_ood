package ru.iandreyshev.gumballmachine.useCase.interfaces

interface IMainUseCase : IUseCase {
    fun insertCoin()

    fun turnCrank()

    fun removeCoin()

    fun fill(newBallsCount: Int)

    fun reset()

    fun switchMachine()
}
