package ru.iandreyshev.gumballmachine.useCase.interfaces

import ru.iandreyshev.gumballmachine.presenter.interfaces.IMachinePresenter

interface IMachineUseCase : IUseCase<IMachinePresenter> {
    fun insertQuarter()

    fun turnCrank()

    fun removeQuarter()

    fun reset()
}
