package ru.iandreyshev.gumballmachine.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import ru.iandreyshev.gumballmachine.app.GumballMachineApp
import ru.iandreyshev.gumballmachine.interactor.interfaces.IInteractor
import ru.iandreyshev.gumballmachine.interactor.interfaces.IMainInteractor
import ru.iandreyshev.gumballmachine.presenter.interfaces.IMainPresenter
import ru.iandreyshev.gumballmachine.presenter.interfaces.IPresenter
import ru.iandreyshev.gumballmachine.useCase.interfaces.IMainUseCase
import ru.iandreyshev.gumballmachine.useCase.interfaces.IUseCase
import ru.iandreyshev.gumballmachine.viewModel.MainViewModel
import ru.iandreyshev.gumballmachine.viewModel.interfaces.AbstractViewModel
import kotlin.reflect.KClass

object CleanArchitectureFactory : ViewModelProvider.Factory {
    override fun <TViewModel : ViewModel?> create(modelClass: Class<TViewModel>): TViewModel =
            when (modelClass) {
                MainViewModel::class.java -> injectDependencies(
                        viewModel = MainViewModel(GumballMachineApp.instance),
                        interactorClass = IMainInteractor::class,
                        presenterClass = IMainPresenter::class,
                        useCaseClass = IMainUseCase::class
                )
                else -> throw IllegalArgumentException("Unknown view model class")
            } as TViewModel

    private fun <
            TInteractor : IInteractor,
            TPresenter : IPresenter,
            TUseCase : IUseCase>

            injectDependencies(

            viewModel: AbstractViewModel<TInteractor>,
            interactorClass: KClass<TInteractor>,
            presenterClass: KClass<TPresenter>,
            useCaseClass: KClass<TUseCase>

    ): AbstractViewModel<TInteractor> {
        val presenter = PresenterFactory.create(presenterClass, viewModel)
        val useCase = UseCaseFactory.create(useCaseClass, presenter)
        viewModel.interactor = InteractorFactory.create(interactorClass, useCase) as TInteractor
        return viewModel
    }
}
