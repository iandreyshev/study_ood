package ru.iandreyshev.adobekiller.presenter.interfaces

import ru.iandreyshev.adobekiller.presenter.interfaces.IPresenter

interface IProgressPresenter : IPresenter {
    fun startProcess()

    fun finishProcess()
}
