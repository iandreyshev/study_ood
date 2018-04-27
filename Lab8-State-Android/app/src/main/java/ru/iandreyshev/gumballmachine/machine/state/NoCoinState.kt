package ru.iandreyshev.gumballmachine.machine.state

import ru.iandreyshev.gumballmachine.machine.GumballMachineError

internal class NoCoinState(
        override val context: IGumballMachineContext,
        override val errorHandler: (GumballMachineError) -> Unit
) : MachineState() {
    override fun insertCoin() {
        if (isMaxCoinsInserted) {
            error { message = "Inserted max coins" }
            return
        }

        context.insertCoin()
        context.setHasCoinState()
    }

    override fun ejectCoin() =
            error { message = "Coins not inserted yet" }

    override fun turnCrank() =
            error { message = "Please insert coin to get the ball" }

    override fun dispense() =
            error { message = "Sorry, I can`t dispense ball to you" }

    override fun fill(newBallsCount: Int) =
            context.fill(newBallsCount)

    override fun toString() = "No coin"
}
