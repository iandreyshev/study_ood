package ru.iandreyshev.gumballmachine.machine.state

import ru.iandreyshev.gumballmachine.machine.GumballMachineError

internal class NoQuarterState(
        override val context: IGumballMachineContext,
        override val errorHandler: (GumballMachineError) -> Unit
) : MachineState() {
    override fun insertQuarter() {
        if (isMaxQuartersInserted) {
            error { message = "Inserted max quarters" }
            return
        }

        context.insertQuarter()
        context.setHasQuarterState()
    }

    override fun ejectQuarter() {
        if (!isQuarterInserted) {
            error { message = "Quarters not inserted yet" }
            return
        }

        context.releaseQuarter()
    }

    override fun turnCrank() =
            error { message = "Please insert quarter to get the ball" }

    override fun dispense() =
            error { message = "Please insert quarter to get the ball" }
}
