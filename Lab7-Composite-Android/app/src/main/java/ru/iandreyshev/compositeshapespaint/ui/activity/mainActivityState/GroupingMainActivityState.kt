package ru.iandreyshev.compositeshapespaint.ui.activity.mainActivityState

import android.support.v7.view.ActionMode
import android.view.Menu
import ru.iandreyshev.compositeshapespaint.R
import ru.iandreyshev.compositeshapespaint.app.ShapesApp
import ru.iandreyshev.compositeshapespaint.ui.viewModel.main.MainActivityState

class GroupingMainActivityState : MainActivityState() {
    override val title: String by lazy { ShapesApp.instance.getString(R.string.act_grouping) }
    override var actionCallback: ActionMode.Callback? = this

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        mode?.menuInflater?.inflate(R.menu.grouping_menu, menu)
        return false
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        context?.interactor?.beginNormal()
    }
}
