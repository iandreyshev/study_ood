package ru.iandreyshev.adobeKiller.di

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import ru.iandreyshev.adobeKiller.di.factory.IPresenterFactory
import ru.iandreyshev.adobeKiller.interactor.ICanvasInteractor
import ru.iandreyshev.adobeKiller.presentation.ui.activity.CanvasActivity
import ru.iandreyshev.adobeKiller.presentation.viewModel.CanvasViewModel
import ru.iandreyshev.adobeKiller.presentation.viewModel.ICanvasViewModel

class DependencyFactory(
        private val vmFactory: ViewModelProvider.Factory,
        private val presenterFactory: IPresenterFactory
) {

    operator fun get(activity: CanvasActivity): ActivityDependency<ICanvasInteractor, ICanvasViewModel> {
        val viewModel = ViewModelProviders.of(activity, vmFactory)
                .get(CanvasViewModel::class.java)

        val appModelPresenter = presenterFactory
                .getCanvasAppModelPresenter(viewModel)

        viewModel.interactor
                .canvasAppModel
                .setPresenter(appModelPresenter)

        val canvasPresenter = presenterFactory
                .getCanvasPresenter(viewModel)

        viewModel.interactor
                .canvasAppModel
                .canvas
                .setPresenter(canvasPresenter)

        return ActivityDependency(
                interactor = viewModel.interactor,
                viewModel = viewModel
        )

    }

}
