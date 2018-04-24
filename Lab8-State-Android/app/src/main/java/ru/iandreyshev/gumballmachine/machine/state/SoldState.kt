package ru.iandreyshev.gumballmachine.machine.state

import ru.iandreyshev.gumballmachine.machine.GumballMachineError

internal class SoldState(
        override val context: IGumballMachineContext,
        override val errorHandler: (GumballMachineError) -> Unit
) : MachineState() {
    override fun insertQuarter() {
    }

    override fun ejectQuarter() {
    }

    override fun turnCrank() {
    }

    override fun dispense() {
    }
}
