package ru.iandreyshev.gumballmachine.machine.state

import ru.iandreyshev.gumballmachine.machine.GumballMachineError

internal class SoldOutState(
        override val context: IGumballMachineContext,
        override val errorHandler: (GumballMachineError) -> Unit
) : MachineState() {
    override fun insertCoin() =
            error { message = "You can't insert a quarter, the machine is sold out" }

    override fun ejectCoin() {
        if (!isCoinInserted) {
            error { message = "You can't eject, you haven't inserted a quarter yet" }
            return
        }

        context.releaseCoins()
    }

    override fun turnCrank() =
            error { message = "You turned but there's no gumballs" }

    override fun dispense() =
            error { message = "No gumball dispensed" }

    override fun fill(newBallsCount: Int) =
            context.fill(newBallsCount)

    override fun toString() = "Sold out"
}
