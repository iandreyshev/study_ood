package ru.iandreyshev.adobeKiller.domain.controller.interfaces

import ru.iandreyshev.adobeKiller.domain.canvasEngine.CanvasData

interface IViewController {

    companion object {
        var canvas: CanvasData = CanvasData(
                id = 0,
                name = "Canvas"
        )
    }

}
