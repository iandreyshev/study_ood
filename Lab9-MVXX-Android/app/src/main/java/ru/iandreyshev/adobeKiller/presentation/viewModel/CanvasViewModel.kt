package ru.iandreyshev.adobeKiller.presentation.viewModel

import android.arch.lifecycle.MutableLiveData
import ru.iandreyshev.adobeKiller.app.UseCaseType
import ru.iandreyshev.adobeKiller.domain.useCase.interfaces.IUseCase
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawable
import ru.iandreyshev.adobeKiller.presentation.interactor.interfaces.ICanvasInteractor
import ru.iandreyshev.adobeKiller.presentation.ui.targetFrame.ITargetCanvasObject
import ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces.InteractorViewModel
import ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces.ICanvasViewModel

class CanvasViewModel : InteractorViewModel<ICanvasInteractor>(UseCaseType.CANVAS), ICanvasViewModel {

    private val mSceneObjects = mutableListOf<IDrawable>()

    // OBSERVABLES
    val title = MutableLiveData<String>()
    val objects = MutableLiveData<List<IDrawable>>()
    val target = MutableLiveData<ITargetCanvasObject?>()
    val invalidating = MutableLiveData<Unit>()
    // OBSERVABLES

    init {
        title.postValue(IUseCase.canvas.name)
    }

    override fun setTarget(target: ITargetCanvasObject?) {
        this.target.postValue(target)
    }

    override fun insert(canvasObject: IDrawable) {
        mSceneObjects.add(canvasObject)
        objects.postValue(mSceneObjects)
    }

    override fun invalidate() =
            invalidating.postValue(Unit)

    override fun clear() {
        mSceneObjects.clear()
        objects.postValue(mSceneObjects)
    }

}
