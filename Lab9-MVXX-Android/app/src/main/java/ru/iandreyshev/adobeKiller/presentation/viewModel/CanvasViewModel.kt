package ru.iandreyshev.adobeKiller.presentation.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import ru.iandreyshev.adobeKiller.interactor.CanvasActivityInteractor
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawable
import ru.iandreyshev.adobeKiller.presentation.ui.targetFrame.ITargetCanvasObject

class CanvasViewModel(
        val interactor: CanvasActivityInteractor
) : ViewModel(), ICanvasViewModel {

    // OBSERVABLES
    override val objects = MutableLiveData<List<IDrawable>>()
    override val target = MutableLiveData<ITargetCanvasObject?>()
    // OBSERVABLES

    init {
        target.postValue(null)
    }

}
