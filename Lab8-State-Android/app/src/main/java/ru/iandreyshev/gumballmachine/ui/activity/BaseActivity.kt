package ru.iandreyshev.gumballmachine.ui.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import ru.iandreyshev.gumballmachine.app.GumballMachineApp
import ru.iandreyshev.gumballmachine.factory.interactor.InteractorFactory
import ru.iandreyshev.gumballmachine.factory.presenter.PresenterFactory
import ru.iandreyshev.gumballmachine.factory.useCase.UseCaseFactory
import ru.iandreyshev.gumballmachine.factory.viewModel.CleanArchitectureFactory
import ru.iandreyshev.gumballmachine.interactor.interfaces.IInteractor
import ru.iandreyshev.gumballmachine.viewModel.interfaces.AbstractViewModel
import kotlin.reflect.KClass

abstract class BaseActivity<TInteractor : IInteractor<*>, in TViewModel : AbstractViewModel<TInteractor>>(
        private val modelClass: KClass<TViewModel>,
        @LayoutRes private val layout: Int
) : AppCompatActivity() {
    protected var interactor: TInteractor? = null

    protected abstract fun onProvideViewModel(viewModel: TViewModel)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)

        val factory = CleanArchitectureFactory(
                application = GumballMachineApp.instance,
                interactorFactory = InteractorFactory,
                presenterFactory = PresenterFactory,
                useCaseFactory = UseCaseFactory
        )

        ViewModelProviders.of(this, factory)
                .get(modelClass.java)
                .let {
                    onProvideViewModel(it)
                }
    }
}
