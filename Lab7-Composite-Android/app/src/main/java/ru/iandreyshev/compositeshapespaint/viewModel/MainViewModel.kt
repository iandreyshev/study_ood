package ru.iandreyshev.compositeshapespaint.viewModel

import android.arch.lifecycle.MutableLiveData
import ru.iandreyshev.compositeshapespaint.app.ShapesApp
import ru.iandreyshev.compositeshapespaint.interactor.interfaces.IMainInteractor
import ru.iandreyshev.compositeshapespaint.model.shape.*
import ru.iandreyshev.compositeshapespaint.viewModel.interfaces.AbstractViewModel
import ru.iandreyshev.compositeshapespaint.viewModel.interfaces.IMainViewModel

class MainViewModel : AbstractViewModel<IMainInteractor>(
        ShapesApp.instance
), IMainViewModel {

    enum class ViewState {
        NORMAL,
        RESIZE,
        MOVE
    }

    // OBSERVABLES
    val targetShape = MutableLiveData<IShape>()
    val shapes = MutableLiveData<List<IShape>>()
    val state = MutableLiveData<ViewState>()
    // OBSERVABLES

    override fun draw(shapes: List<IShape>) {
        this.shapes.postValue(shapes)
    }

    override fun setTarget(shape: IShape?) {
        targetShape.postValue(shape)
    }
}
