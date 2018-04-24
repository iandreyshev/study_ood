package ru.iandreyshev.gumballmachine.viewModel.abstractions

import android.app.Application
import ru.iandreyshev.gumballmachine.interactor.interfaces.IMachineInteractor

abstract class AbstractMachineViewModel(app: Application) : AbstractViewModel<IMachineInteractor>(app)
