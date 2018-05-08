package ru.iandreyshev.adobekiller.ui.viewModel.main

import android.arch.lifecycle.MutableLiveData
import ru.iandreyshev.adobekiller.interactor.interfaces.IMainInteractor
import ru.iandreyshev.adobekiller.model.shape.IShape
import ru.iandreyshev.adobekiller.model.observable.LiveEvent
import ru.iandreyshev.adobekiller.ui.ActionError
import ru.iandreyshev.adobekiller.ui.activity.mainActivityState.GroupingMainActivityState
import ru.iandreyshev.adobekiller.ui.activity.mainActivityState.NormalMainActivityState
import ru.iandreyshev.adobekiller.ui.viewModel.interfaces.IMainViewModel
import ru.iandreyshev.adobekiller.ui.viewModel.interfaces.ProgressViewModel

class MainViewModel : ProgressViewModel<IMainInteractor>(), IMainViewModel {

    var isAttachedFirstTime = true
    private val mNormalState = NormalMainActivityState()
    private val mGroupingState = GroupingMainActivityState()

    // OBSERVABLES
    val targetShape = MutableLiveData<IShape>()
    val shapes = MutableLiveData<List<IShape>>()
    val state = MutableLiveData<MainActivityState>()
    var error = LiveEvent<ActionError?>(null)
    // OBSERVABLES

    override fun updateShapes(shapes: List<IShape>) {
        this.shapes.postValue(shapes)
        targetShape.postValue(targetShape.value)
    }

    override fun setTarget(shape: IShape?) =
            targetShape.postValue(shape)

    override fun beginNormal() =
            state.postValue(mNormalState)

    override fun beginGrouping() =
            state.postValue(mGroupingState)
}
