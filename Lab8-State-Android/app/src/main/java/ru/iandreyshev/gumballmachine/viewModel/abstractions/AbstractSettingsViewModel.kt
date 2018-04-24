package ru.iandreyshev.gumballmachine.viewModel.abstractions

import android.app.Application
import ru.iandreyshev.gumballmachine.interactor.interfaces.ISettingsInteractor

abstract class AbstractSettingsViewModel(app: Application) : AbstractViewModel<ISettingsInteractor>(app)
