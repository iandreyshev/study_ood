package ru.iandreyshev.gumballmachine.machine.state

import ru.iandreyshev.gumballmachine.machine.GumballMachineError

internal class SoldState(
        override val context: IGumballMachineContext,
        override val errorHandler: (GumballMachineError) -> Unit
) : MachineState() {
    override fun insertQuarter() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun ejectQuarter() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun turnCrank() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dispense() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
