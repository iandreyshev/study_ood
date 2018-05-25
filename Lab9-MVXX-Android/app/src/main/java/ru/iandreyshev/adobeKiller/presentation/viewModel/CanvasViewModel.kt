package ru.iandreyshev.adobeKiller.presentation.viewModel

import android.arch.lifecycle.MutableLiveData
import ru.iandreyshev.adobeKiller.app.UseCaseType
import ru.iandreyshev.adobeKiller.presentation.interactor.interfaces.ICanvasInteractor
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawable
import ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces.InteractorViewModel
import ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces.ICanvasViewModel

class CanvasViewModel : InteractorViewModel<ICanvasInteractor>(UseCaseType.CANVAS), ICanvasViewModel {

    private val mDrawables = mutableListOf<IDrawable>()

    var isAttachedFirstTime = true

    // OBSERVABLES
    val targetDrawable = MutableLiveData<IDrawable?>()
    val drawables = MutableLiveData<List<IDrawable>>()
    val title = MutableLiveData<String>()
    // OBSERVABLES

    override fun setCanvasName(canvasName: String) =
            title.postValue(canvasName)

    override fun setTarget(id: Long?) {
        if (id == null) {
            targetDrawable.postValue(null)
            return
        }

        targetDrawable.postValue(mDrawables.find { it.id == id })
    }

    override fun insert(drawable: IDrawable) {
        mDrawables.add(drawable)
        drawables.postValue(mDrawables)
    }

    override fun clear() {
        mDrawables.clear()
        drawables.postValue(listOf())
    }

}
