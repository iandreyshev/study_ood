package ru.iandreyshev.adobeKiller.presentation.viewModel

import android.arch.lifecycle.MutableLiveData
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawable
import ru.iandreyshev.adobeKiller.presentation.ui.targetFrame.ITargetCanvasObject

interface ICanvasViewModel {

    // OBSERVABLES
    val objects: MutableLiveData<List<IDrawable>>
    val target: MutableLiveData<ITargetCanvasObject?>
    // OBSERVABLES

}
