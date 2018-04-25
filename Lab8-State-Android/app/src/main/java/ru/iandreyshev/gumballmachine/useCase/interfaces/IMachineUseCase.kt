package ru.iandreyshev.gumballmachine.useCase.interfaces

import ru.iandreyshev.gumballmachine.presenter.interfaces.IMachinePresenter

interface IMachineUseCase : IUseCase<IMachinePresenter> {
    fun insertCoin()

    fun turnCrank()

    fun removeCoin()

    fun fill(newBallsCount: Int)

    fun reset()
}
