package ru.iandreyshev.gumballmachine.machine

interface IGumballMachine {
    var eventsHandler: IMachineEventsHandler

    val data: GumballMachineData

    fun fill(newBallsCount: Int)

    fun insertCoin()

    fun ejectCoin()

    fun turnCrank()

    fun reset()
}
