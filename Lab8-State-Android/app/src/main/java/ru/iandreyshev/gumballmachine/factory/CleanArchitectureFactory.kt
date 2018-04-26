package ru.iandreyshev.gumballmachine.factory

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import ru.iandreyshev.gumballmachine.interactor.interfaces.IInteractorFactory
import ru.iandreyshev.gumballmachine.presenter.interfaces.IPresenterFactory
import ru.iandreyshev.gumballmachine.useCase.interfaces.IUseCaseFactory
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
import ru.iandreyshev.gumballmachine.viewModel.interfaces.AbstractViewModel
import kotlin.reflect.KClass

class CleanArchitectureFactory(
        private val application: Application,
        private val presenterFactory: IPresenterFactory,
        private val useCaseFactory: IUseCaseFactory,
        private val interactorFactory: IInteractorFactory
) : ViewModelProvider.Factory {

    override fun <TViewModel : ViewModel?> create(modelClass: Class<TViewModel>): TViewModel =
            when (modelClass) {
                MachineViewModel::class.java -> injectDependencies(
                        viewModel = MachineViewModel(application),
                        interactorClass = IMachineInteractor::class,
                        presenterClass = IMachinePresenter::class,
                        useCaseClass = IMachineUseCase::class
                )
                SettingsViewModel::class.java -> injectDependencies(
                        viewModel = SettingsViewModel(application),
                        interactorClass = ISettingsInteractor::class,
                        presenterClass = ISettingsPresenter::class,
                        useCaseClass = ISettingsUseCase::class
                )
                else -> throw IllegalArgumentException("Unknown view model class")
            } as TViewModel

    private fun <TInteractor : IInteractor,
            TPresenter : IPresenter,
            TUseCase : IUseCase>

            injectDependencies(

            viewModel: AbstractViewModel<TInteractor>,
            interactorClass: KClass<TInteractor>,
            presenterClass: KClass<TPresenter>,
            useCaseClass: KClass<TUseCase>

    ): AbstractViewModel<TInteractor> {

        val presenter = presenterFactory.create(presenterClass, viewModel)
        val useCase = useCaseFactory.create(useCaseClass, presenter)
        viewModel.interactor = interactorFactory.create(interactorClass, useCase) as TInteractor
        return viewModel
    }
}
