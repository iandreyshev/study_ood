package ru.iandreyshev.gumballmachine.machine.state

import ru.iandreyshev.gumballmachine.machine.GumballMachineError

internal abstract class MachineState {
    protected abstract val context: IGumballMachineContext

    protected open val errorHandler: (GumballMachineError) -> Unit = {}

    protected val isMaxCoinsInserted: Boolean
        get() = context.data.maxCoinsCount <= context.data.insertedCoinsCount

    protected val isCoinInserted: Boolean
        get() = context.data.insertedCoinsCount > 0

    protected val isBallsExist: Boolean
        get() = context.data.ballsCount > 0

    abstract fun fill(newBallsCount: Int)

    abstract fun insertCoin()

    abstract fun ejectCoin()

    abstract fun turnCrank()

    abstract fun dispense()

    protected fun error(errorAction: GumballMachineError.() -> Unit) {
        val error = GumballMachineError()
        errorAction(error)
        errorHandler(error)
    }
}
