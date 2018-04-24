package ru.iandreyshev.gumballmachine.machine.state

import ru.iandreyshev.gumballmachine.machine.GumballMachineError

internal class HasQuarterState(
        override val context: IGumballMachineContext,
        override val errorHandler: (GumballMachineError) -> Unit
) : MachineState() {
    override fun insertQuarter() {
        if (isMaxQuartersInserted) {
            error { message = "Inserted max quarters" }
            return
        }

        context.insertQuarter()
    }

    override fun ejectQuarter() {
        context.releaseQuarter()

        if (!isMaxQuartersInserted) {
            context.setNoQuarterState()
        }
    }

    override fun turnCrank() {
        if (isBallsExist) {

        }
    }

    override fun dispense() {
    }
}
