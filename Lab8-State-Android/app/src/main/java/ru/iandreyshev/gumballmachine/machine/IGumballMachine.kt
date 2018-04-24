package ru.iandreyshev.gumballmachine.machine

interface IGumballMachine {
    var eventsHandler: IMachineEventsHandler

    val data: GumballMachineData

    fun fill(newBallsCount: Int)

    fun insertQuarter()

    fun ejectQuarter()

    fun turnCrank()

    fun reset()
}
