package ru.iandreyshev.gumballmachine.ui.activity

import ru.iandreyshev.gumballmachine.R
import ru.iandreyshev.gumballmachine.interactor.interfaces.IMachineInteractor
import ru.iandreyshev.gumballmachine.viewModel.interfaces.IMachineViewModel

class MainActivity : BaseActivity<IMachineInteractor, IMachineViewModel>(
        IMachineViewModel::class,
        R.layout.activity_main) {
    override fun onProvideViewModel(viewModel: IMachineViewModel) {
    }
}
