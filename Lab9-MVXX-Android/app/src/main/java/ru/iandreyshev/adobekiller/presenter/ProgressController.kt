package ru.iandreyshev.adobekiller.presenter

import ru.iandreyshev.adobekiller.presenter.interfaces.IProgressPresenter
import ru.iandreyshev.adobekiller.ui.viewModel.interfaces.IProgressViewModel

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
