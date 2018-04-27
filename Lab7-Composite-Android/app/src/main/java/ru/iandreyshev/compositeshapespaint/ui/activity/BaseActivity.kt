package ru.iandreyshev.compositeshapespaint.ui.activity

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import ru.iandreyshev.gumballmachine.interactor.interfaces.IInteractor
import ru.iandreyshev.compositeshapespaint.viewModel.interfaces.AbstractViewModel
import kotlin.reflect.KClass

abstract class BaseActivity<TInteractor : IInteractor, in TViewModel : AbstractViewModel<TInteractor>>(
        private val viewModelFactory: ViewModelProvider.Factory,
        private val viewModelClass: KClass<TViewModel>,
        @LayoutRes private val layout: Int
) : AppCompatActivity() {
    protected var interactor: TInteractor? = null

    protected abstract val onProvideViewModel: (TViewModel.() -> Unit)

    protected abstract fun onActivityCreated(savedInstanceState: Bundle?)

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)

        ViewModelProviders.of(this, viewModelFactory)
                .get(viewModelClass.java)
                .let { viewModel ->
                    interactor = viewModel.interactor
                    onProvideViewModel(viewModel)
                }

        onActivityCreated(savedInstanceState)
    }

    protected fun <TValue> LiveData<TValue>.observe(action: (TValue?) -> Unit) {
        this.observe(this@BaseActivity, android.arch.lifecycle.Observer<TValue?> {
            action(it)
        })
    }
}
