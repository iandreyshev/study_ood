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
import ru.iandreyshev.canvas.style.IConstStyle
import ru.iandreyshev.geometry.frame.IConstFrame

class CanvasPresenter(
        private val viewModel: ICanvasViewModel
) : ICanvasPresenter {

    override fun update() {
        viewModel.objects.postValue(viewModel.objects.value)
    }

    override fun update(objects: List<CanvasObject>) {
        val newObjects = objects.map { newDrawable(it) }
        viewModel.objects.postValue(newObjects)
        viewModel.target.postValue(null)
    }

    private fun newTarget(drawable: IDrawable, canvasObject: CanvasObject): ITargetCanvasObject {

        return object : ITargetCanvasObject {
            override val frame: Frame = ObservableFrame(drawable.frame, ::redraw)
            override val style: Style = ObservableStyle(drawable.style, ::redraw)

            override fun applyChanges() {
                canvasObject.update(frame)
                canvasObject.update(style)
            }

            override fun delete() {
                canvasObject.delete()
            }

            private fun redraw(newFrame: IConstFrame) {
                drawable.frame copyFrom newFrame
                this@CanvasPresenter.update()
            }

            private fun redraw(newStyle: IConstStyle) {
                drawable.style copyFrom newStyle
                this@CanvasPresenter.update()
            }
        }

    }

    private fun newDrawable(canvasObject: CanvasObject): IDrawable {
        var result: DrawableView? = null

        val creator = object : ICanvasObjectVisitor {
            override fun visit(shape: CanvasShape) {
                result = when (shape.type) {
                    ShapeType.Rect -> RectView(Frame(shape.frame), Style(shape.style))
                    ShapeType.Ellipse -> EllipseView(Frame(shape.frame), Style(shape.style))
                    ShapeType.Triangle -> TriangleView(Frame(shape.frame), Style(shape.style))
                }
            }

            override fun visit(image: CanvasImage) {
                result = ImageView(
                        frame = Frame(image.frame),
                        image = image.image
                                ?: Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888)
                )
            }
        }

        canvasObject.accept(creator)

        result?.let { newDrawable ->
            newDrawable.onSelectListener = { drawable ->
                viewModel.target.postValue(newTarget(drawable, canvasObject))
            }

            newDrawable.onDeleteListener = {
                canvasObject.delete()
            }
        }

        return result!!
    }

}
