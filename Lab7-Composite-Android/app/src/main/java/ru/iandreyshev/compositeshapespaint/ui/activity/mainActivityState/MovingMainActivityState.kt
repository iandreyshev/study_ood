package ru.iandreyshev.compositeshapespaint.ui.activity.mainActivityState

import android.support.v7.view.ActionMode
import ru.iandreyshev.compositeshapespaint.R
import ru.iandreyshev.compositeshapespaint.app.ShapesApp
import ru.iandreyshev.compositeshapespaint.model.container.Vec2f
import ru.iandreyshev.compositeshapespaint.ui.viewModel.main.MainActivityState

class MovingMainActivityState : MainActivityState() {
    override val title: String by lazy { ShapesApp.instance.getString(R.string.act_moving) }
    override var actionCallback: ActionMode.Callback? = this

    override fun onContextAttached() {
        context?.setRefreshingLayoutEnabled(false)
    }

    override fun handleCanvasTouchMove(lastX: Float?, lastY: Float?, newX: Float, newY: Float) {
        lastX ?: return
        lastY ?: return

        context?.apply {
            targetShape?.apply {
                val currPosition = frame.position
                val dX = newX - lastX
                val dY = newY - lastY
                frame.position = Vec2f(currPosition.x + dX, currPosition.y + dY)
                interactor.updateShape(this)
            }
        }
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        context?.interactor?.beginNormal()
    }
}
