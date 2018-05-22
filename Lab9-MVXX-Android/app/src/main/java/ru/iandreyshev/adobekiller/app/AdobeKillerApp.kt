package ru.iandreyshev.adobeKiller.app

import android.annotation.SuppressLint
import android.app.Application
import io.objectbox.BoxStore
import ru.iandreyshev.adobeKiller.presentation.interactor.interfaces.IInteractor
import ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces.InteractorViewModel
import ru.iandreyshev.localstorage.ILocalStorage
import ru.iandreyshev.localstorage.LocalStorage
import ru.iandreyshev.localstorage.entity.MyObjectBox

class AdobeKillerApp : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: AdobeKillerApp
    }

    lateinit var localStorage: ILocalStorage
    lateinit var presenterFactory: PresenterFactory
    lateinit var interactorFactory: InteractorFactory
    lateinit var useCaseFactory: UseCaseFactory

    override fun onCreate() {
        super.onCreate()
        instance = this

        val boxStore = MyObjectBox.builder()
                .androidContext(applicationContext)
                .build()

        initLocalStorage(boxStore)
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

    private fun initLocalStorage(boxStore: BoxStore) {
        localStorage = LocalStorage(boxStore)
    }

    private fun initFactories() {
        presenterFactory = PresenterFactory()
        interactorFactory = InteractorFactory()
        useCaseFactory = UseCaseFactory(
                localStorage = localStorage
        )
    }

}
