package ru.iandreyshev.gumballmachine.machine.state

import ru.iandreyshev.gumballmachine.machine.GumballMachineError

internal abstract class MachineState {
    protected abstract val context: IGumballMachineContext

    protected open val errorHandler: (GumballMachineError) -> Unit = {}

    protected val isMaxQuartersInserted: Boolean
        get() = context.data.maxQuartersCount <= context.data.insertedQuartersCount

    protected val isQuarterInserted: Boolean
        get() = context.data.insertedQuartersCount > 0

    protected val isBallsExist: Boolean
        get() = context.data.ballsCount > 0

    abstract fun insertQuarter()

    abstract fun ejectQuarter()

    abstract fun turnCrank()

    abstract fun dispense()

    protected fun error(errorAction: GumballMachineError.() -> Unit) {
        val error = GumballMachineError()
        errorAction(error)
        errorHandler(error)
    }
}
