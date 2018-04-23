package ru.iandreyshev.gumballmachine.factory.presenter

import ru.iandreyshev.gumballmachine.presenter.MachinePresenter
import ru.iandreyshev.gumballmachine.viewModel.MainViewModel

object PresenterFactory : IPresenterFactory<MachinePresenter, MainViewModel> {
    override fun create(viewModel: MainViewModel): MachinePresenter =
            MachinePresenter(viewModel)
}
