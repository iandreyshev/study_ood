package ru.iandreyshev.gumballmachine.viewModel

import android.app.Application
import ru.iandreyshev.gumballmachine.viewModel.interfaces.IMachineViewModel

class MachineViewModel(app: Application) : IMachineViewModel(app) {
    override fun onError(error: String) {
    }
}
