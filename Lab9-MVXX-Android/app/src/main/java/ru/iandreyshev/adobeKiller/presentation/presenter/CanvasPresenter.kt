package ru.iandreyshev.adobeKiller.presentation.presenter

import android.graphics.Bitmap
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.*
import ru.iandreyshev.adobeKiller.presentation.drawing.extension.copyFrom
import ru.iandreyshev.geometry.frame.Frame
import ru.iandreyshev.geometry.frame.ObservableFrame
import ru.iandreyshev.canvas.style.ObservableStyle
import ru.iandreyshev.canvas.style.Style
import ru.iandreyshev.canvas.presenter.ICanvasPresenter
import ru.iandreyshev.adobeKiller.presentation.ui.targetFrame.ITargetCanvasObject
import ru.iandreyshev.adobeKiller.presentation.viewModel.ICanvasViewModel
import ru.iandreyshev.canvas.core.*

class CanvasPresenter(
        private val viewModel: ICanvasViewModel
) : ICanvasPresenter {

    override fun redraw(newObjects: List<CanvasObject>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insert(canvasObject: CanvasObject) {
        val drawable = newDrawable(canvasObject)
        viewModel.insert(drawable)
    }

    override fun redraw() =
            viewModel.reDraw()

    override fun clear() =
            viewModel.clear()

    private fun newTarget(drawable: DrawableView, canvasObject: CanvasObject): ITargetCanvasObject {

        return object : ITargetCanvasObject {

            override val frame: Frame = ObservableFrame(drawable.frame, ::reDraw)
            override val style: Style = ObservableStyle(drawable.style, ::reDraw)

            override fun applyChanges() {
                canvasObject.update(frame, style)
            }

            override fun delete() {
                canvasObject.delete()
                viewModel.setTarget(null)
            }

            // TODO: Refactor this
            // This code is dangerous cause frame and style can be not initialized
            // when apply changes call
            private fun reDraw() {
                drawable.frame copyFrom frame
                drawable.style copyFrom style
                this@CanvasPresenter.redraw()
            }
        }

    }

    private fun newDrawable(canvasObject: CanvasObject): IDrawable {
        var result: DrawableView? = null

        val creator = object : ICanvasObjectVisitor {
            override fun visit(shape: CanvasShape) {
                result = when (shape.type) {
                    ShapeType.Rect -> RectView(shape.frame, shape.style)
                    ShapeType.Ellipse -> EllipseView(shape.frame, shape.style)
                    ShapeType.Triangle -> TriangleView(shape.frame, shape.style)
                }
            }

            override fun visit(image: CanvasImage) {
                result = ImageView(
                        frame = image.frame,
                        image = image.image
                                ?: Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888)
                )
            }
        }

        canvasObject.accept(creator)

        result?.let { drawable ->
            drawable.setOnClickListener {
                viewModel.setTarget(newTarget(drawable, canvasObject))
            }
            drawable.setOnDeleteListener {
                canvasObject.delete()
            }
            return drawable
        }

        throw RuntimeException("Invalid creator code...")
    }

}
