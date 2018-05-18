package ru.iandreyshev.adobeKiller.app

import android.annotation.SuppressLint
import android.app.Application
import io.objectbox.BoxStore
import io.objectbox.android.AndroidObjectBrowser
import io.objectbox.android.BuildConfig
import ru.iandreyshev.adobeKiller.data.IRepository
import ru.iandreyshev.adobeKiller.data.MyObjectBox
import ru.iandreyshev.adobeKiller.data.Repository
import ru.iandreyshev.adobeKiller.presentation.interactor.interfaces.IInteractor
import ru.iandreyshev.adobeKiller.presentation.ui.viewModel.interfaces.InteractorViewModel

class AdobeKillerApp : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: AdobeKillerApp
    }

    private lateinit var localStorage: BoxStore
    private lateinit var repository: IRepository

    private lateinit var presenterFactory: PresenterFactory
    private lateinit var interactorFactory: InteractorFactory
    private lateinit var useCaseFactory: UseCaseFactory

    override fun onCreate() {
        super.onCreate()
        instance = this

        initLocalStorage()
        initRepository()
        initFactories()
    }

    fun <TInteractor : IInteractor> injectDependencies(
            viewModel: InteractorViewModel<TInteractor>,
            data: Any? = null
    ): InteractorViewModel<TInteractor> {
        val presenter = presenterFactory.create(viewModel.useCaseType, viewModel)
        val useCase = useCaseFactory.create(viewModel.useCaseType, presenter, data)
        viewModel.interactor = interactorFactory.create(viewModel.useCaseType, useCase) as TInteractor
        return viewModel
    }

    private fun initLocalStorage() {
        localStorage = MyObjectBox.builder().androidContext(this).build()
    }

    private fun initRepository() {
        repository = Repository(
                localStorage = localStorage
        )
    }

    private fun initFactories() {
        presenterFactory = PresenterFactory()
        interactorFactory = InteractorFactory()
        useCaseFactory = UseCaseFactory(
                repository = repository
        )
    }

}
