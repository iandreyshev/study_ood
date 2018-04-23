package ru.iandreyshev.gumballmachine.ui.activity

import ru.iandreyshev.gumballmachine.R
import ru.iandreyshev.gumballmachine.interactor.interfaces.IMachineInteractor
import ru.iandreyshev.gumballmachine.viewModel.abstractions.AbstractMachineViewModel

class MainActivity : BaseActivity<IMachineInteractor, AbstractMachineViewModel>(
        AbstractMachineViewModel::class,
        R.layout.activity_main
) {
    override fun onProvideViewModel(viewModel: AbstractMachineViewModel) {
    }
}
