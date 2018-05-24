package ru.iandreyshev.adobeKiller.domain.presentationModel

import android.graphics.Bitmap
import ru.iandreyshev.adobeKiller.domain.command.ICommandQueue
import ru.iandreyshev.adobeKiller.domain.model.CanvasObjectData
import ru.iandreyshev.adobeKiller.domain.model.ShapeType
import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.Color

class PresentationModel(
        private val commandQueue: ICommandQueue
) : IPresentationModel {

    override val data: List<CanvasObjectData>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun fill(shape: CanvasObjectData) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insert(shape: ShapeType): CanvasObjectData {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insert(image: Bitmap): CanvasObjectData {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun resize(id: Long, width: Float, height: Float) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun move(id: Long, x: Float, y: Float) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun changeFillColor(id: Long, color: Color) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun changeStrokeColor(id: Long, color: Color) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun resizeStroke(id: Long, size: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun undo() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun redo() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun refresh() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
