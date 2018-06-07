package ru.iandreyshev.adobeKiller.presentation.ui.activity

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity

abstract class BaseAppCompatActivity(
        @LayoutRes private val layout: Int
) : AppCompatActivity() {

    protected abstract fun onLayoutCreated(savedInstanceState: Bundle?)

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        onLayoutCreated(savedInstanceState)
    }

    protected fun <TValue> LiveData<TValue>.observe(action: (TValue?) -> Unit) {
        this.observe(this@BaseAppCompatActivity, Observer<TValue> {
            action(it)
        })
    }

    protected fun <TValue> LiveData<TValue>.observeNotNull(action: (TValue) -> Unit) {
        this.observe(this@BaseAppCompatActivity, Observer<TValue> {
            it?.apply(action)
        })
    }

}
