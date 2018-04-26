package ru.iandreyshev.gumballmachine.useCase

import ru.iandreyshev.gumballmachine.machine.GumballMachineError
import ru.iandreyshev.gumballmachine.machine.IGumballMachine
import ru.iandreyshev.gumballmachine.presenter.interfaces.IMainPresenter
import ru.iandreyshev.gumballmachine.useCase.interfaces.IMainUseCase

class MainUseCase(
        private var presenter: IMainPresenter,
        machineWithState: IGumballMachine,
        machineWithEnums: IGumballMachine
) : IMainUseCase {

    private var mCurrentMachine: IGumballMachine = machineWithState
    private var mSwapMachine = {
        mCurrentMachine = when (mCurrentMachine) {
            machineWithEnums -> machineWithState
            machineWithState -> machineWithEnums
            else -> mCurrentMachine
        }
    }

    init {
        machineWithState.errorHandler = { presenter.onError(it) }
        machineWithEnums.errorHandler = { presenter.onError(it) }
        presenter.updateMachineData(mCurrentMachine.data)
    }

    override fun insertCoin() =
            actionWrap { mCurrentMachine.insertCoin() }

    override fun turnCrank() =
            actionWrap { mCurrentMachine.turnCrank() }

    override fun removeCoin() =
            actionWrap { mCurrentMachine.ejectCoin() }

    override fun fill(newBallsCount: Int) =
            actionWrap { mCurrentMachine.fill(newBallsCount) }

    override fun reset() =
            actionWrap { mCurrentMachine.reset() }

    override fun switchMachine() =
            actionWrap { mSwapMachine() }

    private fun actionWrap(action: IGumballMachine.() -> Unit) {
        try {
            action(mCurrentMachine)
            presenter.updateMachineData(mCurrentMachine.data)
        } catch (ex: Exception) {
            ex.printStackTrace()
            presenter.onError(GumballMachineError(ex.message ?: "Invalid error"))
        }
    }
}
