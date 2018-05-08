package ru.iandreyshev.adobekiller.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import ru.iandreyshev.adobekiller.interactor.interfaces.IMainInteractor
import ru.iandreyshev.adobekiller.presenter.interfaces.IMainPresenter
import ru.iandreyshev.adobekiller.useCase.interfaces.IMainUseCase
import ru.iandreyshev.adobekiller.ui.viewModel.main.MainViewModel
import ru.iandreyshev.adobekiller.ui.viewModel.interfaces.AbstractViewModel
import ru.iandreyshev.adobekiller.interactor.interfaces.IInteractor
import ru.iandreyshev.adobekiller.presenter.interfaces.IPresenter
import ru.iandreyshev.adobekiller.useCase.interfaces.IUseCase
import kotlin.reflect.KClass

object CleanArchitectureFactory : ViewModelProvider.Factory {
    override fun <TViewModel : ViewModel?> create(modelClass: Class<TViewModel>): TViewModel =
            when (modelClass) {
                MainViewModel::class.java -> injectDependencies(
                        viewModel = MainViewModel(),
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
