package ru.iandreyshev.gumballmachine.useCase

import ru.iandreyshev.gumballmachine.presenter.interfaces.ISettingsPresenter
import ru.iandreyshev.gumballmachine.useCase.interfaces.ISettingsUseCase

class SettingsUseCase(
        private var presenter: ISettingsPresenter
) : ISettingsUseCase
