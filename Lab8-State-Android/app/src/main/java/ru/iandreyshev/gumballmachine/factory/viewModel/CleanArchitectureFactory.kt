package ru.iandreyshev.gumballmachine.factory.viewModel

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import ru.iandreyshev.gumballmachine.factory.interactor.IInteractorFactory
import ru.iandreyshev.gumballmachine.factory.presenter.IPresenterFactory
import ru.iandreyshev.gumballmachine.factory.useCase.IUseCaseFactory
import ru.iandreyshev.gumballmachine.interactor.interfaces.IInteractor
import ru.iandreyshev.gumballmachine.interactor.interfaces.IMachineInteractor
import ru.iandreyshev.gumballmachine.interactor.interfaces.ISettingsInteractor
import ru.iandreyshev.gumballmachine.presenter.interfaces.IMachinePresenter
import ru.iandreyshev.gumballmachine.presenter.interfaces.IPresenter
import ru.iandreyshev.gumballmachine.presenter.interfaces.ISettingsPresenter
import ru.iandreyshev.gumballmachine.useCase.interfaces.IMachineUseCase
import ru.iandreyshev.gumballmachine.useCase.interfaces.ISettingsUseCase
import ru.iandreyshev.gumballmachine.useCase.interfaces.IUseCase
import ru.iandreyshev.gumballmachine.viewModel.MachineViewModel
import ru.iandreyshev.gumballmachine.viewModel.SettingsViewModel
import ru.iandreyshev.gumballmachine.viewModel.interfaces.IMachineViewModel
import ru.iandreyshev.gumballmachine.viewModel.interfaces.AbstractViewModel
import kotlin.reflect.KClass

class CleanArchitectureFactory(
        private val application: Application,
        private val presenterFactory: IPresenterFactory,
        private val useCaseFactory: IUseCaseFactory,
        private val interactorFactory: IInteractorFactory
) : ViewModelProvider.Factory {
    override fun <TViewModel : ViewModel?> create(modelClass: Class<TViewModel>): TViewModel {
        val viewModel = when (modelClass) {
            IMachineViewModel::class.java -> MachineViewModel(application)
                    .injectDependencies(
                            interactorClass = IMachineInteractor::class,
                            presenterClass = IMachinePresenter::class,
                            useCaseClass = IMachineUseCase::class
                    )
            SettingsViewModel::class.java -> SettingsViewModel(application)
                    .injectDependencies(
                            interactorClass = ISettingsInteractor::class,
                            presenterClass = ISettingsPresenter::class,
                            useCaseClass = ISettingsUseCase::class
                    )
            else -> throw IllegalArgumentException("Unknown view model class")
        }
        return viewModel as TViewModel
    }

    private fun <TInteractor : IInteractor<*>, TPresenter : IPresenter<*>, TUseCase : IUseCase<*>>
            AbstractViewModel<TInteractor>.injectDependencies(
            interactorClass: KClass<TInteractor>,
            presenterClass: KClass<TPresenter>,
            useCaseClass: KClass<TUseCase>): AbstractViewModel<TInteractor> {
        val presenter = presenterFactory.create(presenterClass, this)
        val useCase = useCaseFactory.create(useCaseClass, presenter)
        interactor = interactorFactory.create(interactorClass, useCase)
        return this
    }
}
