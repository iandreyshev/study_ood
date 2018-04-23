package ru.iandreyshev.gumballmachine.factory.viewModel

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import ru.iandreyshev.gumballmachine.factory.interactor.IInteractorFactory
import ru.iandreyshev.gumballmachine.factory.presenter.IPresenterFactory
import ru.iandreyshev.gumballmachine.factory.useCase.IUseCaseFactory
import ru.iandreyshev.gumballmachine.interactor.interfaces.IMachineInteractor
import ru.iandreyshev.gumballmachine.interactor.interfaces.ISettingsInteractor
import ru.iandreyshev.gumballmachine.presenter.interfaces.IMachinePresenter
import ru.iandreyshev.gumballmachine.presenter.interfaces.ISettingsPresenter
import ru.iandreyshev.gumballmachine.useCase.interfaces.IMachineUseCase
import ru.iandreyshev.gumballmachine.useCase.interfaces.ISettingsUseCase
import ru.iandreyshev.gumballmachine.viewModel.MachineViewModel
import ru.iandreyshev.gumballmachine.viewModel.SettingsViewModel
import ru.iandreyshev.gumballmachine.viewModel.abstractions.AbstractViewModel

class CleanArchitectureFactory(
        private val application: Application,
        private val presenterFactory: IPresenterFactory,
        private val useCaseFactory: IUseCaseFactory,
        private val interactorFactory: IInteractorFactory
) : ViewModelProvider.Factory {
    override fun <TViewModel : ViewModel?> create(modelClass: Class<TViewModel>): TViewModel {
        val viewModel = when (modelClass) {
            MachineViewModel::class -> create(MachineViewModel(application)) { model ->
                val presenter = presenterFactory.create(IMachinePresenter::class).apply {
                    viewModel = model
                }
                val useCase = useCaseFactory.create(IMachineUseCase::class).apply {
                    this.presenter = presenter
                }
                val interactor = interactorFactory.create(IMachineInteractor::class).apply {
                    this.useCase = useCase
                }
            }
            SettingsViewModel::class -> create(SettingsViewModel(application)) { model ->
                val presenter = presenterFactory.create(ISettingsPresenter::class).apply {
                    viewModel = model
                }
                val useCase = useCaseFactory.create(ISettingsUseCase::class).apply {
                    this.presenter = presenter
                }
                val interactor = interactorFactory.create(ISettingsInteractor::class).apply {
                    this.useCase = useCase
                }
            }
            else -> throw IllegalArgumentException("Unknown view model class")
        }

        return viewModel as TViewModel
    }

    private fun <TModel : AbstractViewModel> create(model: TModel, buildAction: (TModel) -> Unit): TModel {
        buildAction(model)
        return model
    }
}
