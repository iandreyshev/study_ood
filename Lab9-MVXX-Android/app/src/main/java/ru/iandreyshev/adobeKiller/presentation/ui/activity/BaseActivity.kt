package ru.iandreyshev.adobeKiller.presentation.ui.activity

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import ru.iandreyshev.adobeKiller.app.AdobeKillerApp
import ru.iandreyshev.adobeKiller.presentation.interactor.interfaces.IInteractor
import ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces.InteractorViewModel
import kotlin.reflect.KClass

abstract class BaseActivity<TInteractor : IInteractor, in TViewModel : InteractorViewModel<TInteractor>>(
        private val viewModelClass: KClass<TViewModel>,
        @LayoutRes private val layout: Int
) : AppCompatActivity() {

    protected lateinit var interactor: TInteractor

    protected abstract val onProvideViewModel: (TViewModel.() -> Unit)

    protected abstract fun onLayoutCreated(savedInstanceState: Bundle?)

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        onLayoutCreated(savedInstanceState)

        ViewModelProviders.of(this)
                .get(viewModelClass.java)
                .let { viewModel ->
                    AdobeKillerApp.instance.injectDependencies(viewModel)
                    interactor = viewModel.interactor
                            ?: throw IllegalStateException("Invalid interactor factory that can not create ${interactor::class}")
                    onProvideViewModel(viewModel)
                }
    }

    protected fun <TValue> LiveData<TValue>.observe(action: (TValue?) -> Unit) {
        this.observe(this@BaseActivity, Observer<TValue> {
            action(it)
        })
    }

    protected fun <TValue> LiveData<TValue>.observeNotNull(action: (TValue) -> Unit) {
        this.observe(this@BaseActivity, Observer<TValue> {
            it?.apply(action)
        })
    }

}
