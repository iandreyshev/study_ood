package ru.iandreyshev.adobeKiller.app

import android.app.Application
import io.objectbox.BoxStore
import ru.iandreyshev.adobeKiller.domain.controller.interfaces.IViewController
import ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces.ControllerViewModel
import ru.iandreyshev.localstorage.ILocalStorage
import ru.iandreyshev.localstorage.LocalStorage
import ru.iandreyshev.localstorage.entity.MyObjectBox

class AdobeKillerApp : Application() {

    companion object {
        lateinit var instance: AdobeKillerApp
    }

    lateinit var localStorage: ILocalStorage
    lateinit var presenterFactory: PresenterFactory
    lateinit var viewControllerFactory: ViewControllerFactory

    override fun onCreate() {
        super.onCreate()
        instance = this

        val boxStore = MyObjectBox.builder()
                .androidContext(applicationContext)
                .build()

        initLocalStorage(boxStore)
        initFactories()
    }

    fun <TController : IViewController> injectDependencies(
            viewModel: ControllerViewModel<TController>,
            data: Any? = null
    ): ControllerViewModel<TController> {
        val presenter = presenterFactory.create(viewModel.viewControllerType, viewModel)
        viewModel.controller = viewControllerFactory.create(viewModel.viewControllerType, presenter, data) as TController
        return viewModel
    }

    private fun initLocalStorage(boxStore: BoxStore) {
        localStorage = LocalStorage(boxStore)
    }

    private fun initFactories() {
        presenterFactory = PresenterFactory()
        viewControllerFactory = ViewControllerFactory(localStorage)
    }

}
