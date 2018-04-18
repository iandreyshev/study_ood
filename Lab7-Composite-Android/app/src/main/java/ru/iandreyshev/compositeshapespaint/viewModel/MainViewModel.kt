package ru.iandreyshev.compositeshapespaint.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import containers.Vec2i
import shape.IShape
import ru.iandreyshev.compositeshapespaint.model.shape.Rectangle

class MainViewModel : ViewModel() {
    // OBSERVABLES
    val targetShape = MutableLiveData<IShape>()
    val shapes = MutableLiveData<HashSet<IShape>>()
    // OBSERVABLES

    init {
        refresh()
    }

    fun setTarget(shape: IShape) {
        if (shapes.value?.contains(shape) == true) {
            targetShape.postValue(shape)
        }
    }

    fun refresh() {
        val listOfShapes = hashSetOf<IShape>()

        repeat(16) {
            listOfShapes.add(Rectangle(Vec2i(it, it), Vec2i(it + it * it, it + it * it)))
        }

        shapes.postValue(listOfShapes)
    }
}
