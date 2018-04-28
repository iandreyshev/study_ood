package ru.iandreyshev.compositeshapespaint.useCase

import ru.iandreyshev.compositeshapespaint.model.canvas.Color
import ru.iandreyshev.compositeshapespaint.model.shape.IShape
import ru.iandreyshev.compositeshapespaint.presenter.interfaces.IMainPresenter
import ru.iandreyshev.compositeshapespaint.ui.shapes.ImageGenerator
import ru.iandreyshev.compositeshapespaint.useCase.interfaces.IMainUseCase

class MainUseCase(
        private var presenter: IMainPresenter
) : IMainUseCase {

    private var mShapes = ImageGenerator.create()

    init {
        processWrap {
            presenter.updateShapes(mShapes)
            presenter.setTarget(null)
        }
    }

    override fun setState(stateName: String) =
            presenter.setState(stateName.trim().toLowerCase())

    override fun setTargetShape(shape: IShape) {
        if (!mShapes.contains(shape)) {
            return // TODO: Create notification about invalid target shape
        }

        presenter.setTarget(shape)
    }

    override fun resizeStroke(shape: IShape, size: Int) {
    }

    override fun changeFillColor(shape: IShape, color: Color) {
    }

    override fun changeStrokeColor(shape: IShape, color: Color) {
    }

    override fun deleteShape(shape: IShape) {
    }

    override fun refresh() = processWrap {
        mShapes = ImageGenerator.create()
        presenter.updateShapes(mShapes)
    }

    private fun processWrap(process: () -> Unit) {
        presenter.startProcess()
        process.invoke()
        presenter.finishProcess()
    }
}
