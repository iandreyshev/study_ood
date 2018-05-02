package ru.iandreyshev.compositeshapespaint.useCase

import ru.iandreyshev.compositeshapespaint.model.canvas.Color
import ru.iandreyshev.compositeshapespaint.model.shape.IShape
import ru.iandreyshev.compositeshapespaint.presenter.interfaces.IMainPresenter
import ru.iandreyshev.compositeshapespaint.useCase.interfaces.IMainUseCase
import ru.iandreyshev.compositeshapespaint.model.shape.IShapeFactory

class MainUseCase(
        private var presenter: IMainPresenter,
        private val shapesFactory: IShapeFactory,
        private val onRefresh: () -> List<IShape> = { listOf() }
) : IMainUseCase {

    private lateinit var mShapes: MutableList<IShape>

    init {
        refresh()
    }

    override fun addShape(shapeName: String) = processWrap {
        shapesFactory.create(shapeName).let { shape ->
            if (shape == null) {
                return@processWrap // TODO: Notify UI about invalid shape name
            }

            mShapes.add(shape)
            presenter.setTarget(shape)
            presenter.updateShapes(mShapes)
        }
    }

    override fun setState(stateName: String) =
            presenter.setState(stateName.trim().toLowerCase())

    override fun setTargetShape(shape: IShape?) {
        if (shape == null) {
            presenter.setTarget(null)
            return
        }

        if (!mShapes.contains(shape)) {
            return // TODO: Create notification about invalid target shape
        }

        presenter.setTarget(shape)
    }

    override fun updateShape(shape: IShape) {
        presenter.updateShapes(mShapes)
    }

    override fun resizeStroke(shape: IShape, size: Int) {
        shape.style.setStrokeSize(size.toFloat())
        updateShape(shape)
    }

    override fun changeFillColor(shape: IShape, color: Color) {
        shape.style.setFillColor(color)
        updateShape(shape)
    }

    override fun changeStrokeColor(shape: IShape, color: Color) {
        shape.style.setStrokeColor(color)
        updateShape(shape)
    }

    override fun deleteShape(shape: IShape) = processWrap {
        mShapes.remove(shape)
        presenter.updateShapes(mShapes)
        presenter.setTarget(null)
    }

    override fun refresh() = processWrap {
        mShapes = ArrayList(onRefresh())
        presenter.updateShapes(mShapes)
        presenter.setTarget(null)
        presenter.setState("normal")
    }

    private fun processWrap(process: () -> Unit) {
        presenter.startProcess()
        process.invoke()
        presenter.finishProcess()
    }
}
