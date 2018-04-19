package ru.iandreyshev.compositeshapespaint.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import canvas.Color
import ru.iandreyshev.compositeshapespaint.model.containers.Vec2f
import ru.iandreyshev.compositeshapespaint.model.shape.Ellipse
import ru.iandreyshev.compositeshapespaint.model.shape.IShape
import ru.iandreyshev.compositeshapespaint.model.shape.Rectangle

class MainViewModel : ViewModel() {
    // OBSERVABLES
    val targetShape = MutableLiveData<IShape>()
    val shapes = MutableLiveData<List<IShape>>()
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
        val listOfShapes = mutableListOf<IShape>(
                Rectangle(
                        leftTop = Vec2f(200, 200),
                        rightBottom = Vec2f(400, 700),
                        strokeColor = Color.BLACK,
                        fillColor = Color.RED),
                Ellipse(
                        center = Vec2f(400, 700),
                        horizontalRadius = 250f,
                        verticalRadius = 250f,
                        fillColor = Color.WHITE,
                        strokeColor = Color.BLACK,
                        strokeSize = 20f),
                Ellipse(
                        center = Vec2f(200, 700),
                        horizontalRadius = 250f,
                        verticalRadius = 250f,
                        fillColor = Color.WHITE,
                        strokeColor = Color.BLACK,
                        strokeSize = 20f))

        shapes.postValue(listOfShapes)
        targetShape.postValue(null)
    }
}
