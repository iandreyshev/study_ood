package ru.iandreyshev.gumballmachine.viewModel.interfaces

import android.app.Application
import ru.iandreyshev.gumballmachine.interactor.interfaces.ISettingsInteractor

abstract class ISettingsViewModel(app: Application) : AbstractViewModel<ISettingsInteractor>(app)
