package ru.iandreyshev.gumballmachine.presenter

import ru.iandreyshev.gumballmachine.presenter.interfaces.ISettingsPresenter
import ru.iandreyshev.gumballmachine.viewModel.interfaces.ISettingsViewModel

class SettingsPresenter(
        private val viewModel: ISettingsViewModel
) : ISettingsPresenter
