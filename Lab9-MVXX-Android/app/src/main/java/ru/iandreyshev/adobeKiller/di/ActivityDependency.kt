package ru.iandreyshev.adobeKiller.di

data class ActivityDependency<TInteractor, TViewModel>(
        val interactor: TInteractor,
        val viewModel: TViewModel
)
