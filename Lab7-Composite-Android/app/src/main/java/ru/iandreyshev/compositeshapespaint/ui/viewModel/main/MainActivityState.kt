package ru.iandreyshev.compositeshapespaint.ui.viewModel.main

import android.support.v7.view.ActionMode
import android.view.Menu
import ru.iandreyshev.compositeshapespaint.ui.activity.mainActivityState.EmptyActionCallback

abstract class MainActivityState : ActionMode.Callback by EmptyActionCallback() {
    var context: IMainActivityStateContext? = null
        set(value) {
            field = value
            onContextAttached()
        }

    open var actionCallback: ActionMode.Callback? = null
        protected set

    open fun handleCanvasTouchMove(lastX: Float?, lastY: Float?, newX: Float, newY: Float) {
        // skip
    }

    protected abstract val title: String

    protected open fun onContextAttached() {
        // skip
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        mode?.title = title
        return true
    }
}
