package ru.iandreyshev.gumballmachine.interactor.interfaces

interface IMainInteractor : IInteractor {
    fun insertCoin()

    fun turnCrank()

    fun removeCoin()

    fun fill(newBallsCount: Int)

    fun reset()

    fun switchMachine()
}
