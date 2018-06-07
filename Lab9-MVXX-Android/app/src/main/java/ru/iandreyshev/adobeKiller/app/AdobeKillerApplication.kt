package ru.iandreyshev.adobeKiller.app

import android.app.Application
import ru.iandreyshev.adobeKiller.di.DependencyFactory
import ru.iandreyshev.adobeKiller.di.InteractorFactory
import ru.iandreyshev.adobeKiller.di.PresenterFactory
import ru.iandreyshev.adobeKiller.di.ViewModelFactory
import ru.iandreyshev.adobeKiller.model.CanvasApplicationModel
import ru.iandreyshev.canvas.core.Canvas
import ru.iandreyshev.command.CommandQueue
import ru.iandreyshev.localstorage.CanvasStorage
import ru.iandreyshev.localstorage.entity.MyObjectBox

class AdobeKillerApplication : Application() {

    companion object {
        private const val COMMAND_QUEUE_SIZE = 12

        lateinit var dependencyFactory: DependencyFactory
    }

    override fun onCreate() {
        super.onCreate()

        val boxStore = MyObjectBox.builder()
                .androidContext(applicationContext)
                .build()

        val commandQueue = CommandQueue(COMMAND_QUEUE_SIZE)

        val applicationModel = CanvasApplicationModel(
                commandQueue = commandQueue,
                canvas = Canvas(
                        commandQueue = commandQueue,
                        serializer = CanvasStorage(boxStore)
                )
        )
        val interactorFactory = InteractorFactory(
                applicationModel = applicationModel
        )
        val vmFactory = ViewModelFactory(
                interactorFactory = interactorFactory
        )
        dependencyFactory = DependencyFactory(
                vmFactory = vmFactory,
                presenterFactory = PresenterFactory()
        )

    }

}
