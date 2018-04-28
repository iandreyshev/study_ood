package ru.iandreyshev.compositeshapespaint.viewModel.main

import android.support.v7.widget.Toolbar

abstract class MainActivityState {
    var context: IMainActivityStateContext? = null

    abstract fun tuneToolbar(toolbar: Toolbar)
}
