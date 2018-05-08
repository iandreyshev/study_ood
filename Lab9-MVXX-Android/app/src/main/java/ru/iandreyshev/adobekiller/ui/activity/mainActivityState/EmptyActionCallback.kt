package ru.iandreyshev.adobekiller.ui.activity.mainActivityState

import android.support.v7.view.ActionMode
import android.view.Menu
import android.view.MenuItem

class EmptyActionCallback : ActionMode.Callback {
    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        return true // skip
    }

    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        return true // skip
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        return true
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        // skip
    }
}