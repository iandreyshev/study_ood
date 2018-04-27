package ru.iandreyshev.compositeshapespaint.useCase

import ru.iandreyshev.compositeshapespaint.model.shape.IShape
import ru.iandreyshev.compositeshapespaint.presenter.interfaces.IMainPresenter
import ru.iandreyshev.compositeshapespaint.ui.shapes.ImageGenerator
import ru.iandreyshev.compositeshapespaint.useCase.interfaces.IMainUseCase

class MainUseCase(
        private var presenter: IMainPresenter
) : IMainUseCase {

    private var shapes = ImageGenerator.create()

    init {
        presenter.draw(shapes)
        presenter.setTarget(null)
    }

    override fun selectShape(shape: IShape) =
            presenter.setTarget(shape)

    override fun refresh() {
        shapes = ImageGenerator.create()
        presenter.draw(shapes)
    }

    override fun resize(args: String) {
    }

    override fun move(args: String) {
    }

    override fun resizeStroke(args: String) {
    }

    override fun changeFillColor(args: String) {
    }

    override fun changeStrokeColor(args: String) {
    }
}
