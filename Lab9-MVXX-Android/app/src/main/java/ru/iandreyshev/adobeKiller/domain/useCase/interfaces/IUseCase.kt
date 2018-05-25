package ru.iandreyshev.adobeKiller.domain.useCase.interfaces

import ru.iandreyshev.adobeKiller.domain.model.CanvasData

interface IUseCase {

    companion object {
        var canvas: CanvasData = CanvasData(
                id = 0,
                name = "Canvas"
        )
    }

}
