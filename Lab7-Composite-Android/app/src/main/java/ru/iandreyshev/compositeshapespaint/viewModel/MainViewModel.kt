package ru.iandreyshev.compositeshapespaint.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import canvas.Color
import ru.iandreyshev.compositeshapespaint.model.containers.Vec2f
import ru.iandreyshev.compositeshapespaint.model.shape.*
import ru.iandreyshev.compositeshapespaint.ui.ActionError
import ru.iandreyshev.compositeshapespaint.ui.shapes.ImageGenerator

class MainViewModel : ViewModel() {
    companion object {
        private class ActionException(val error: ActionError) : Throwable()
    }

    // OBSERVABLES
    val actionError = MutableLiveData<ActionError?>()
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
        shapes.postValue(ImageGenerator.get())
        targetShape.postValue(null)
    }

    fun resize(args: String) = action {
        val size = args.split(" ")
        val width = size.getOrNull(0)?.toFloatOrNull()
                ?: throw ActionException(ActionError.MOVE_ARGS_ERR)
        val height = size.getOrNull(1)?.toFloatOrNull()
                ?: throw ActionException(ActionError.MOVE_ARGS_ERR)

        frame.resize(width, height)
    }

    fun move(args: String) = action {
        val coordinates = args.split(" ")
        val x = coordinates.getOrNull(0)?.toFloatOrNull()
                ?: throw ActionException(ActionError.MOVE_ARGS_ERR)
        val y = coordinates.getOrNull(1)?.toFloatOrNull()
                ?: throw ActionException(ActionError.MOVE_ARGS_ERR)

        frame.position = Vec2f(x, y)
    }

    fun resizeStroke(args: String) = action {
        val size = args.trim().toFloatOrNull()
                ?: throw ActionException(ActionError.RESIZE_STROKE_ERR)
        setStrokeSize(size)
    }

    fun changeFillColor(args: String) = action {
        val color = args.asColorOrNull
                ?: throw ActionException(ActionError.INVALID_COLOR)
        setFillColor(color)
    }

    fun changeStrokeColor(args: String) = action {
        val color = args.asColorOrNull
                ?: throw ActionException(ActionError.INVALID_COLOR)
        setStrokeColor(color)
    }

    private fun action(action: IShape.() -> Unit) {
        val target = targetShape.value

        if (target == null) {
            actionError.postValue(ActionError.SHAPE_NOT_SELECTED)
            return
        }

        try {
            action(target)
            shapes.postValue(shapes.value)
            targetShape.postValue(targetShape.value)
        } catch (ex: ActionException) {
            actionError.postValue(ex.error)
        } catch (ex: Exception) {
            actionError.postValue(ActionError.UNDEFINED_ERR)
        }
        actionError.postValue(null)
    }

    private val String.asColorOrNull: Color?
        get() = try {
            Color.valueOf(trim().toUpperCase())
        } catch (ex: Exception) {
            null
        }
}
