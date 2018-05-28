package ru.iandreyshev.adobeKiller.presentation.viewModel

import android.arch.lifecycle.MutableLiveData
import ru.iandreyshev.adobeKiller.app.UseCaseType
import ru.iandreyshev.adobeKiller.domain.model.CanvasObject
import ru.iandreyshev.adobeKiller.presentation.interactor.interfaces.ICanvasInteractor
import ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces.InteractorViewModel
import ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces.ICanvasViewModel

class CanvasViewModel : InteractorViewModel<ICanvasInteractor>(UseCaseType.CANVAS), ICanvasViewModel {

    private val mCanvasObjects = mutableListOf<CanvasObject>()

    // OBSERVABLES
    val title = MutableLiveData<String>()
    val objects = MutableLiveData<List<CanvasObject>>()
    val targetObject = MutableLiveData<CanvasObject?>()
    // OBSERVABLES

    override fun setCanvasName(canvasName: String) =
            title.postValue(canvasName)

    override fun setTarget(canvasObject: CanvasObject?) =
            targetObject.postValue(canvasObject)

    override fun insert(canvasObject: CanvasObject) {
        mCanvasObjects.add(canvasObject)
        objects.postValue(mCanvasObjects)
    }

    override fun clear() {
        mCanvasObjects.clear()
        objects.postValue(mCanvasObjects)
    }

}
