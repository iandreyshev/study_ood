package ru.iandreyshev.compositeshapespaint.presenter

import ru.iandreyshev.compositeshapespaint.presenter.interfaces.IProgressPresenter
import ru.iandreyshev.compositeshapespaint.ui.viewModel.interfaces.IProgressViewModel

class ProgressController(
        private val viewModel: IProgressViewModel
) : IProgressPresenter {

    private var mProcessCounter = 0

    override fun startProcess() =
            updateWrap { ++mProcessCounter }

    override fun finishProcess() =
            updateWrap { --mProcessCounter }

    @Synchronized
    private fun updateWrap(action: () -> Unit) {
        action()
        viewModel.startProgress(mProcessCounter > 0)
    }
}
