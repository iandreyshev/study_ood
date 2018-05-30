package ru.iandreyshev.adobeKiller.presentation.viewModel

import android.arch.lifecycle.MutableLiveData
import ru.iandreyshev.adobeKiller.app.ViewControllerType
import ru.iandreyshev.adobeKiller.domain.canvasEngine.CanvasData
import ru.iandreyshev.adobeKiller.domain.controller.interfaces.IMenuViewController
import ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces.IMenuViewModel
import ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces.ControllerViewModel

class MenuViewModel : ControllerViewModel<IMenuViewController>(ViewControllerType.MENU), IMenuViewModel {

    // OBSERVABLES
    val canvases = MutableLiveData<List<CanvasData>>()
    var onOpen: (() -> Unit) = {}
    // OBSERVABLES

    override fun openCanvas() =
            onOpen()

    override fun setCanvases(canvases: List<CanvasData>) =
            this.canvases.postValue(canvases)

}
