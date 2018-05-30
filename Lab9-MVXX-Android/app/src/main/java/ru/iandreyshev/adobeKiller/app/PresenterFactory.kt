package ru.iandreyshev.adobeKiller.app

import android.graphics.Bitmap
import ru.iandreyshev.adobeKiller.domain.canvasEngine.*
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.*
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.IStyle
import ru.iandreyshev.adobeKiller.presentation.presenter.CanvasPresenter
import ru.iandreyshev.adobeKiller.presentation.presenter.MenuPresenter
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.IPresenter
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.IPresenterFactory
import ru.iandreyshev.adobeKiller.presentation.ui.targetFrame.ITargetCanvasObject
import ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces.ICanvasViewModel
import ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces.IMenuViewModel
import ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces.ControllerViewModel

class PresenterFactory : IPresenterFactory {

    override fun create(
            viewControllerType: ViewControllerType,
            viewModel: ControllerViewModel<*>?): IPresenter = when (viewControllerType) {

        ViewControllerType.MENU -> MenuPresenter(
                viewModel = viewModel as IMenuViewModel)

        ViewControllerType.CANVAS -> CanvasPresenter(
                viewModel = viewModel as ICanvasViewModel,
                targetFactory = object : CanvasPresenter.ITargetFactory {
                    override fun create(canvasObject: CanvasObject) = newTargetObject(canvasObject)
                },
                drawableFactory = object : CanvasPresenter.IDrawableFactory {
                    override fun create(canvasObject: CanvasObject) = newDrawable(canvasObject)
                })

    }

    private fun newTargetObject(canvasObject: CanvasObject) = object : ITargetCanvasObject {
        override val frame: Frame = canvasObject.frame.clone()
        override val style: IStyle = canvasObject.style.clone()
        override fun applyChanges() = canvasObject.update(frame, style)
    }

    private fun newDrawable(canvasObject: CanvasObject): IDrawable {
        var result: IDrawable? = null

        val creator = object : ICanvasObjectVisitor {
            override fun visit(shape: CanvasShape) {
                result = when (shape.type) {
                    ShapeType.Rect -> DrawableRect(shape.frame, shape.style)
                    ShapeType.Ellipse -> DrawableEllipse(shape.frame, shape.style)
                    ShapeType.Triangle -> DrawableTriangle(shape.frame, shape.style)
                }
            }

            override fun visit(image: CanvasImage) {
                result = DrawableImage(
                        width = image.frame.width,
                        height = image.frame.height,
                        image = image.image ?: Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888)
                )
            }
        }

        canvasObject.accept(creator)
        result?.let { return it }

        throw RuntimeException("Invalid creator code...")
    }

}
