package ru.iandreyshev.adobeKiller.domain.useCase

import ru.iandreyshev.adobeKiller.domain.useCase.interfaces.IMenuUseCase
import ru.iandreyshev.adobeKiller.domain.model.CanvasData
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.IMenuPresenter
import ru.iandreyshev.localstorage.ILocalStorage

class MenuUseCase(
        private val localStorage: ILocalStorage,
        private val presenter: IMenuPresenter
) : IMenuUseCase {

    init {
        presenter.setCanvases(localStorage.getCanvases().map { CanvasData(it) })
    }

    override fun createCanvas(name: String) {
        localStorage.createCanvas(name)
        val canvases = localStorage.getCanvases()
        presenter.setCanvases(canvases.map { CanvasData(it) })
    }

    override fun openCanvas(canvasData: CanvasData) =
            presenter.openCanvas(canvasData)


}
