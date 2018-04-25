package ru.iandreyshev.gumballmachine.useCase

import ru.iandreyshev.gumballmachine.machine.GumballMachineError
import ru.iandreyshev.gumballmachine.machine.IGumballMachine
import ru.iandreyshev.gumballmachine.machine.IMachineEventsHandler
import ru.iandreyshev.gumballmachine.presenter.interfaces.IMachinePresenter
import ru.iandreyshev.gumballmachine.useCase.interfaces.IMachineUseCase

class MachineUseCase(
        override var presenter: IMachinePresenter?,
        private val machine: IGumballMachine
) : IMachineUseCase {
    init {
        machine.eventsHandler = EventHandler()
        actionWrap {}
    }

    override fun insertCoin() = actionWrap { machine.insertCoin() }

    override fun turnCrank() = actionWrap { machine.turnCrank() }

    override fun removeCoin() = actionWrap { machine.ejectCoin() }

    override fun fill(newBallsCount: Int) = actionWrap { machine.fill(newBallsCount) }

    override fun reset() = actionWrap { machine.reset() }

    private fun actionWrap(action: IGumballMachine.() -> Unit) {
        try {
            action(machine)
            presenter?.updateMachineData(machine.data)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    inner class EventHandler : IMachineEventsHandler {
        override fun onError(error: GumballMachineError) {
            presenter?.onError(error)
        }
    }
}
