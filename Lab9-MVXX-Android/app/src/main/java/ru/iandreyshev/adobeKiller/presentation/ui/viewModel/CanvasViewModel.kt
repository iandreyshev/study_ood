package ru.iandreyshev.adobeKiller.presentation.ui.viewModel

import android.arch.lifecycle.MutableLiveData
import ru.iandreyshev.adobeKiller.app.UseCaseType
import ru.iandreyshev.adobeKiller.domain.model.CanvasData
import ru.iandreyshev.adobeKiller.presentation.interactor.interfaces.ICanvasInteractor
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawable
import ru.iandreyshev.adobeKiller.presentation.ui.viewModel.interfaces.InteractorViewModel
import ru.iandreyshev.adobeKiller.presentation.ui.viewModel.interfaces.ICanvasViewModel

class CanvasViewModel : InteractorViewModel<ICanvasInteractor>(UseCaseType.CANVAS), ICanvasViewModel {

    var isAttachedFirstTime = true

    // OBSERVABLES
    val targetShape = MutableLiveData<IDrawable>()
    val shapes = MutableLiveData<List<IDrawable>>()
    val canvas = MutableLiveData<CanvasData>()
    // OBSERVABLES

    override fun setCanvasData(canvasData: CanvasData) =
            canvas.postValue(canvasData)

    override fun updateShapes(shapes: List<IDrawable>) {
        this.shapes.postValue(shapes)
        targetShape.postValue(targetShape.value)
    }

    override fun setTarget(shape: IDrawable?) =
            targetShape.postValue(shape)

}
