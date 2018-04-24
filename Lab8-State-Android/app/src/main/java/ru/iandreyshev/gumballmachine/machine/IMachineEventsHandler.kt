package ru.iandreyshev.gumballmachine.machine

interface IMachineEventsHandler {
    fun onReleaseBall() {}

    fun onReleaseQuarter() {}

    fun onError(error: GumballMachineError) {}
}
