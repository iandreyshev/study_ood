package ru.iandreyshev.gumballmachine.machine.state

import ru.iandreyshev.gumballmachine.machine.GumballMachineError

internal class HasCoinState(
        override val context: IGumballMachineContext,
        override val errorHandler: (GumballMachineError) -> Unit
) : MachineState() {
    override fun insertCoin() {
        if (isMaxCoinsInserted) {
            error { message = "Inserted max coins" }
            return
        }

        context.insertCoin()
    }

    override fun ejectCoin() {
        context.releaseCoins()

        if (!isCoinInserted) {
            context.setNoCoinState()
        }
    }

    override fun turnCrank() {
        context.takeCoin()
        context.setSoldState()
    }

    override fun dispense() =
            error { message = "No gumball dispensed" }

    override fun fill(newBallsCount: Int) =
            context.fill(newBallsCount)

    override fun toString() = "Has coin"
}
