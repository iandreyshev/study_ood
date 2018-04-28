package ru.iandreyshev.compositeshapespaint.ui.activity.mainActivityState

import android.support.v7.view.ActionMode
import ru.iandreyshev.compositeshapespaint.R
import ru.iandreyshev.compositeshapespaint.app.ShapesApp
import ru.iandreyshev.compositeshapespaint.ui.viewModel.main.MainActivityState

class ResizingMainActivityState : MainActivityState() {
    override val title: String by lazy { ShapesApp.instance.getString(R.string.act_resizing) }
    override var actionCallback: ActionMode.Callback? = this

    override fun onContextAttached() {
        context?.setRefreshingLayoutEnabled(false)
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        context?.interactor?.beginNormal()
    }
}
