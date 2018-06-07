package ru.iandreyshev.adobeKiller.di.factory

import ru.iandreyshev.adobeKiller.model.CanvasApplicationModel

interface ICanvasFactory {

    fun getCanvas(): CanvasApplicationModel

}
