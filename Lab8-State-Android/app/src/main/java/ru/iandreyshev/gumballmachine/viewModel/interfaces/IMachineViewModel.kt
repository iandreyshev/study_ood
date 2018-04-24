package ru.iandreyshev.gumballmachine.viewModel.interfaces

import android.app.Application
import ru.iandreyshev.gumballmachine.interactor.interfaces.IMachineInteractor

abstract class IMachineViewModel(app: Application) : AbstractViewModel<IMachineInteractor>(app)
