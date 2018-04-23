package ru.iandreyshev.gumballmachine.interactor

import ru.iandreyshev.gumballmachine.interactor.interfaces.ISettingsInteractor
import ru.iandreyshev.gumballmachine.useCase.interfaces.ISettingsUseCase

class SettingsInteractor : ISettingsInteractor {
    override var useCase: ISettingsUseCase? = null
}
