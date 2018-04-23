package ru.iandreyshev.gumballmachine.presenter

import ru.iandreyshev.gumballmachine.presenter.interfaces.ISettingsPresenter
import ru.iandreyshev.gumballmachine.viewModel.abstractions.AbstractSettingsViewModel

class SettingsPresenter : ISettingsPresenter {
    override var viewModel: AbstractSettingsViewModel? = null
}
