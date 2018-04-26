package ru.iandreyshev.gumballmachine.interactor

import ru.iandreyshev.gumballmachine.interactor.interfaces.IMainInteractor
import ru.iandreyshev.gumballmachine.useCase.interfaces.IMainUseCase

class MainInteractor(
        private val useCase: IMainUseCase
) : IMainInteractor {

    override fun insertCoin() =
            useCase.insertCoin()

    override fun turnCrank() =
            useCase.turnCrank()

    override fun removeCoin() =
            useCase.removeCoin()

    override fun fill(newBallsCount: Int) =
            useCase.fill(newBallsCount)

    override fun reset() =
            useCase.reset()

    override fun switchMachine() =
            useCase.switchMachine()
}
