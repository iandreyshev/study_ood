package ru.iandreyshev.adobeKiller.presentation.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import ru.iandreyshev.adobeKiller.interactor.interfaces.ICanvasActivityInteractor
import ru.iandreyshev.canvas.controller.interfaces.IViewController
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawable
import ru.iandreyshev.adobeKiller.presentation.ui.targetFrame.ITargetCanvasObject

class CanvasViewModel(
        val interactor: ICanvasActivityInteractor
) : ViewModel(), ICanvasViewModel {

    private val mSceneObjects = mutableListOf<IDrawable>()

    // OBSERVABLES
    val title = MutableLiveData<String>()
    val objects = MutableLiveData<List<IDrawable>>()
    val target = MutableLiveData<ITargetCanvasObject?>()
    val invalidating = MutableLiveData<Unit>()
    // OBSERVABLES

    init {
        title.postValue(IViewController.canvas.name)
    }

    override fun setTarget(target: ITargetCanvasObject?) {
        this.target.postValue(target)
    }

    override fun insert(canvasObject: IDrawable) {
        mSceneObjects.add(canvasObject)
        objects.postValue(mSceneObjects)
    }

    override fun reDraw() =
            invalidating.postValue(Unit)

    override fun clear() {
        mSceneObjects.clear()
        objects.postValue(mSceneObjects)
    }

}
