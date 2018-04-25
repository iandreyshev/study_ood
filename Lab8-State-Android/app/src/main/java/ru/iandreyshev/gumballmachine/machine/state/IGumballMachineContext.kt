package ru.iandreyshev.gumballmachine.machine.state

import ru.iandreyshev.gumballmachine.machine.GumballMachineData

internal interface IGumballMachineContext {
    val data: GumballMachineData

    fun releaseBall()

    fun insertCoin()

    fun releaseCoins()

    fun takeCoin()

    fun fill(newBallsCount: Int)

    fun setSoldOutState()

    fun setNoCoinState()

    fun setSoldState()

    fun setHasCoinState()
}
