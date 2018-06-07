package ru.iandreyshev.adobeKiller.di

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import ru.iandreyshev.adobeKiller.interactor.interfaces.ICanvasActivityInteractor
import ru.iandreyshev.adobeKiller.presentation.ui.activity.CanvasActivity
import ru.iandreyshev.adobeKiller.presentation.viewModel.CanvasViewModel
import ru.iandreyshev.adobeKiller.presentation.viewModel.ICanvasViewModel

class DependencyFactory(
        private val vmFactory: ViewModelProvider.Factory
) {

    operator fun get(activity: CanvasActivity): ActivityDependency<ICanvasActivityInteractor, ICanvasViewModel> {

        val viewModel = ViewModelProviders.of(activity, vmFactory)
                .get(CanvasViewModel::class.java)

        return ActivityDependency(
                interactor = viewModel.interactor,
                viewModel = viewModel
        )

    }

}
