package ru.iandreyshev.gumballmachine.interactor

import ru.iandreyshev.gumballmachine.interactor.interfaces.IMachineInteractor
import ru.iandreyshev.gumballmachine.useCase.interfaces.IMachineUseCase

class MachineInteractor(
        override val useCase: IMachineUseCase?
) : IMachineInteractor {
    override fun insertCoin() {
        useCase?.insertCoin()
    }

    override fun turnCrank() {
        useCase?.turnCrank()
    }

    override fun removeCoin() {
        useCase?.removeCoin()
    }

    override fun fill(newBallsCount: Int) {
        useCase?.fill(newBallsCount)
    }

    override fun reset() {
        useCase?.reset()
    }
}
