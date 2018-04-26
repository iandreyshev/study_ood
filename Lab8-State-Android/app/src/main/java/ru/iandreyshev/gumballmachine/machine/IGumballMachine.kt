package ru.iandreyshev.gumballmachine.machine

interface IGumballMachine {
    var errorHandler: (GumballMachineError) -> Unit

    val data: GumballMachineData

    fun fill(ballsCount: Int)

    fun insertCoin()

    fun ejectCoin()

    fun turnCrank()

    fun reset()
}
