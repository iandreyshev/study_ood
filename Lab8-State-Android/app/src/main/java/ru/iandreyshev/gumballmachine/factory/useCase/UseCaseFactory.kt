package ru.iandreyshev.gumballmachine.factory.useCase

import ru.iandreyshev.gumballmachine.presenter.MachinePresenter
import ru.iandreyshev.gumballmachine.useCase.MachineUseCase

object UseCaseFactory : IUseCaseFactory<MachineUseCase, MachinePresenter> {
    override fun create(presenter: MachinePresenter): MachineUseCase =
            MachineUseCase(presenter)
}
