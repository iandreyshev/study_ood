package ru.iandreyshev.gumballmachine.machine.state

import ru.iandreyshev.gumballmachine.machine.GumballMachineData

internal interface IGumballMachineContext {
    val data: GumballMachineData

    fun releaseBall()

    fun insertQuarter()

    fun releaseQuarter()

    fun setSoldOutState()

    fun setNoQuarterState()

    fun setSoldState()

    fun setHasQuarterState()
}
