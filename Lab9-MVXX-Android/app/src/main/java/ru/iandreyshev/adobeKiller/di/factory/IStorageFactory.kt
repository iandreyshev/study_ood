package ru.iandreyshev.adobeKiller.di.factory

import ru.iandreyshev.canvas.storage.ICanvasStorage

interface IStorageFactory {

    fun getCanvasStorage(): ICanvasStorage

}
