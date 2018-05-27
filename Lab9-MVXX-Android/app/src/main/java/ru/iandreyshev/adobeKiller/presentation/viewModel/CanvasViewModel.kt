package ru.iandreyshev.adobeKiller.presentation.viewModel

import android.arch.lifecycle.MutableLiveData
import ru.iandreyshev.adobeKiller.app.UseCaseType
import ru.iandreyshev.adobeKiller.domain.model.CanvasObject
import ru.iandreyshev.adobeKiller.presentation.interactor.interfaces.ICanvasInteractor
import ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces.InteractorViewModel
import ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces.ICanvasViewModel

class CanvasViewModel : InteractorViewModel<ICanvasInteractor>(UseCaseType.CANVAS), ICanvasViewModel {

    var isAttachedFirstTime = true

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
        val newList = mutableListOf<CanvasObject>().apply {
            objects.value?.forEach { add(it) }
            add(canvasObject)
        }
        objects.postValue(newList)
    }

    override fun clear() {
        objects.postValue(listOf())
    }

}
