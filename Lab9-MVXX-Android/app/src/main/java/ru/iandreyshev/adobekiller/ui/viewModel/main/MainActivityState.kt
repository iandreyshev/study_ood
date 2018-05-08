package ru.iandreyshev.adobekiller.ui.viewModel.main

import android.support.v7.view.ActionMode
import android.view.Menu
import ru.iandreyshev.adobekiller.model.shape.IShape
import ru.iandreyshev.adobekiller.ui.activity.mainActivityState.EmptyActionCallback
import ru.iandreyshev.adobekiller.ui.viewModel.main.IMainActivityStateContext

abstract class MainActivityState : ActionMode.Callback by EmptyActionCallback() {
    var context: IMainActivityStateContext? = null
        set(value) {
            field = value
            onContextAttached()
        }

    open var actionCallback: ActionMode.Callback? = null
        protected set

    abstract fun onClickOutsideShape()

    abstract fun onShapeSelected(shape: IShape)

    protected abstract val title: String

    protected open fun onContextAttached() {
        // skip
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        mode?.title = title
        return true
    }
}
