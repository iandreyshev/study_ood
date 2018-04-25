package ru.iandreyshev.gumballmachine.machine

interface IMachineEventsHandler {
    fun onReleaseBall() {}

    fun onReleaseCoins() {}

    fun onError(error: GumballMachineError) {}
}
