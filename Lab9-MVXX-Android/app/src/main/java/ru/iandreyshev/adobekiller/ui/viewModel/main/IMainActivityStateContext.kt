package ru.iandreyshev.adobekiller.ui.viewModel.main

import ru.iandreyshev.adobekiller.interactor.interfaces.IMainInteractor
import ru.iandreyshev.adobekiller.model.shape.IShape

interface IMainActivityStateContext {
    var targetShape: IShape?
    val interactor: IMainInteractor
}
