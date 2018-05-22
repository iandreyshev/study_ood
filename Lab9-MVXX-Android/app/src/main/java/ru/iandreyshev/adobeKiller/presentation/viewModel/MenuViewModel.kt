package ru.iandreyshev.adobeKiller.presentation.viewModel

import android.arch.lifecycle.MutableLiveData
import ru.iandreyshev.adobeKiller.app.UseCaseType
import ru.iandreyshev.adobeKiller.presentation.interactor.interfaces.IMenuInteractor
import ru.iandreyshev.adobeKiller.domain.model.CanvasData
import ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces.IMenuViewModel
import ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces.InteractorViewModel

class MenuViewModel : InteractorViewModel<IMenuInteractor>(UseCaseType.MENU), IMenuViewModel {

    // OBSERVABLES
    val canvases = MutableLiveData<List<CanvasData>>()
    var onOpen: (CanvasData) -> Unit = {}
    // OBSERVABLES

    override fun open(canvasData: CanvasData) =
            onOpen(canvasData)

    override fun setCanvases(canvases: List<CanvasData>) =
            this.canvases.postValue(canvases)

}
