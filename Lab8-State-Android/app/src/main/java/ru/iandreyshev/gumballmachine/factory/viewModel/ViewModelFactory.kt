@file:Suppress("UNCHECKED_CAST")

package ru.iandreyshev.gumballmachine.factory.viewModel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import ru.iandreyshev.gumballmachine.factory.interactor.InteractorFactory
import ru.iandreyshev.gumballmachine.factory.presenter.PresenterFactory
import ru.iandreyshev.gumballmachine.factory.useCase.UseCaseFactory
import ru.iandreyshev.gumballmachine.viewModel.MainViewModel

object ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            val viewModel = MainViewModel().apply {
                val presenter = PresenterFactory.create(this)
                val useCase = UseCaseFactory.create(presenter)
                interactor = InteractorFactory.create(useCase)
            }
            return viewModel as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
