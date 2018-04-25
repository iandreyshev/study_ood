package ru.iandreyshev.gumballmachine.interactor.interfaces

interface IMachineInteractor : IInteractor {
    fun insertCoin()

    fun turnCrank()

    fun removeCoin()

    fun fill(newBallsCount: Int)

    fun reset()
}
