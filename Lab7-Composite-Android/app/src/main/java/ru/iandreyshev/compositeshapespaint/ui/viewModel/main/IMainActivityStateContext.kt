package ru.iandreyshev.compositeshapespaint.ui.viewModel.main

import ru.iandreyshev.compositeshapespaint.interactor.interfaces.IMainInteractor
import ru.iandreyshev.compositeshapespaint.model.shape.IShape

interface IMainActivityStateContext {
    var targetShape: IShape?
    val interactor: IMainInteractor

    fun setRefreshingLayoutEnabled(isEnabled: Boolean)
}
