package ru.iandreyshev.adobekiller.ui.activity.mainActivityState

import android.support.v7.view.ActionMode
import ru.iandreyshev.adobekiller.model.shape.IShape
import ru.iandreyshev.adobekiller.ui.viewModel.main.MainActivityState

class NormalMainActivityState : MainActivityState() {
    override val title: String = ""
    override var actionCallback: ActionMode.Callback? = null

    override fun onClickOutsideShape() {
        context?.interactor?.setTargetShape(null)
    }

    override fun onShapeSelected(shape: IShape) {
        context?.interactor?.setTargetShape(shape)
    }
}
