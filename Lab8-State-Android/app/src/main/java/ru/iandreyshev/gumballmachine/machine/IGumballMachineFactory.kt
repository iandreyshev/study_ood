package ru.iandreyshev.gumballmachine.machine

import kotlin.reflect.KClass

interface IGumballMachineFactory {
    fun <TMachine : IGumballMachine> create(machineClass: KClass<TMachine>)
}
