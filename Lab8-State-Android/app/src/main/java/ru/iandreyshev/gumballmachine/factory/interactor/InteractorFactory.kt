package ru.iandreyshev.gumballmachine.factory.interactor

import ru.iandreyshev.gumballmachine.interactor.MachineInteractor
import ru.iandreyshev.gumballmachine.interactor.SettingsInteractor
import ru.iandreyshev.gumballmachine.useCase.MachineUseCase
import ru.iandreyshev.gumballmachine.useCase.SettingsUseCase

object InteractorFactory : IInteractorFactory<MachineInteractor, MachineUseCase>,
        IInteractorFactory<SettingsInteractor, SettingsUseCase> {
    override fun create(useCase: MachineUseCase): MachineInteractor {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun create(useCase: SettingsUseCase): SettingsInteractor {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
