package ru.iandreyshev.adobeKiller.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import ru.iandreyshev.adobeKiller.di.factory.IInteractorFactory
import ru.iandreyshev.adobeKiller.presentation.viewModel.CanvasViewModel

class ViewModelFactory(
        private val interactorFactory: IInteractorFactory
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        val viewModel: ViewModel = when (modelClass) {

            CanvasViewModel::class.java -> {
                CanvasViewModel(
                        interactor = interactorFactory.getCanvasActivityInteractor()
                )
            }

            else -> throw IllegalArgumentException("Invalid view model type!")

        }

        return viewModel as T

    }

}
