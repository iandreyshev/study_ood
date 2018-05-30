package ru.iandreyshev.adobeKiller.domain.controller

import ru.iandreyshev.adobeKiller.domain.extension.toModel
import ru.iandreyshev.adobeKiller.domain.canvasEngine.CanvasData
import ru.iandreyshev.adobeKiller.domain.controller.interfaces.IMenuViewController
import ru.iandreyshev.adobeKiller.domain.controller.interfaces.IViewController
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.IMenuPresenter
import ru.iandreyshev.localstorage.ILocalStorage

class MenuViewController(
        private val localStorage: ILocalStorage,
        private val presenter: IMenuPresenter
) : IMenuViewController {

    init {
        presenter.setCanvases(localStorage.getCanvases().map { it.toModel() })
    }

    override fun createCanvas(name: String) {
        localStorage.createCanvas(name)
        val canvases = localStorage.getCanvases()
        presenter.setCanvases(canvases.map { it.toModel() })
    }

    override fun openCanvas(canvasData: CanvasData) {
        IViewController.canvas = canvasData
        presenter.openCanvas()
    }

    override fun deleteCanvas(canvasData: CanvasData) {
        localStorage.deleteCanvas(canvasData.id)
        val canvases = localStorage.getCanvases()
        presenter.setCanvases(canvases.map { it.toModel() })
    }

}
