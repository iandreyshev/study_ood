package ru.iandreyshev.adobeKiller.di.factory

import ru.iandreyshev.adobeKiller.model.ApplicationModel

interface ICanvasFactory {

    fun getCanvas(): ApplicationModel

}
