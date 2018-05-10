package ru.iandreyshev.gumballmachine.machine.state

import ru.iandreyshev.gumballmachine.machine.GumballMachineError
import ru.iandreyshev.gumballmachine.machine.StateName

internal class SoldState(
        override val context: IGumballMachineContext,
        override val errorHandler: (GumballMachineError) -> Unit
) : MachineState() {
    override fun insertCoin() =
            error { message = "Please wait, we're already giving you a gumball" }

    override fun ejectCoin() =
            error { message = "Sorry you already turned the crank" }

    override fun turnCrank() =
            error { message = "Turning twice doesn't get you another gumball" }

    override fun dispense() {
        context.releaseBall()

        when {
            !isBallsExist -> context.setSoldOutState()
            isCoinInserted -> context.setHasCoinState()
            else -> context.setNoCoinState()
        }
    }

    override fun fill(newBallsCount: Int) =
            error { message = "Can`t to insert balls during release balls" }

    override fun toString() =
            StateName.SOLD
}
