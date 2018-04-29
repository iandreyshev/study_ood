package ru.iandreyshev.compositeshapespaint.ui.viewModel.main

import android.arch.lifecycle.MutableLiveData
import ru.iandreyshev.compositeshapespaint.interactor.interfaces.IMainInteractor
import ru.iandreyshev.compositeshapespaint.model.observable.LiveEvent
import ru.iandreyshev.compositeshapespaint.model.shape.*
import ru.iandreyshev.compositeshapespaint.ui.ActionError
import ru.iandreyshev.compositeshapespaint.ui.activity.mainActivityState.GroupingMainActivityState
import ru.iandreyshev.compositeshapespaint.ui.activity.mainActivityState.NormalMainActivityState
import ru.iandreyshev.compositeshapespaint.ui.viewModel.interfaces.IMainViewModel
import ru.iandreyshev.compositeshapespaint.ui.viewModel.interfaces.ProgressViewModel

class MainViewModel : ProgressViewModel<IMainInteractor>(), IMainViewModel {

    private val mNormalState = NormalMainActivityState()
    private val mGroupingState = GroupingMainActivityState()

    // OBSERVABLES
    val targetShape = MutableLiveData<IShape>()
    val shapes = MutableLiveData<List<IShape>>()
    val state = MutableLiveData<MainActivityState>()
    var error = LiveEvent<ActionError?>(null)
    // OBSERVABLES

    override fun updateShapes(shapes: List<IShape>) =
            this.shapes.postValue(shapes)

    override fun setTarget(shape: IShape?) =
            targetShape.postValue(shape)

    override fun beginNormal() =
            state.postValue(mNormalState)

    override fun beginGrouping() =
            state.postValue(mGroupingState)
}
