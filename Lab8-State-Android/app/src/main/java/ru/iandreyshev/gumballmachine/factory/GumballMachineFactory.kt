package ru.iandreyshev.gumballmachine.factory

import ru.iandreyshev.gumballmachine.machine.IGumballMachine
import ru.iandreyshev.gumballmachine.machine.IGumballMachineFactory
import kotlin.reflect.KClass

class GumballMachineFactory : IGumballMachineFactory {
    override fun <TMachine : IGumballMachine> create(machineClass: KClass<TMachine>) {

    }
}
