package ru.iandreyshev.compositeshapespaint.ui.adapter

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import canvas.Color
import ru.iandreyshev.compositeshapespaint.model.canvas.ICanvas
import ru.iandreyshev.compositeshapespaint.converter.ColorConverter
import ru.iandreyshev.compositeshapespaint.model.containers.Vec2f

class AndroidCanvasAdapter(private val canvas: Canvas) : ICanvas {
    private var mPath = Path()

    override var color: Color = Color.NONE

    override var strokeSize: Float = 0f

    override fun fill() = draw {
        style = Paint.Style.FILL
    }

    override fun stroke() = draw {
        style = Paint.Style.STROKE
    }

    override fun moveTo(dest: Vec2f) {
        mPath.moveTo(dest.x, dest.y)
    }

    override fun lineTo(dest: Vec2f) {
        mPath.lineTo(dest.x, dest.y)
    }

    override fun drawEllipse(center: Vec2f, horizontalRadius: Float, verticalRadius: Float) {
        val left = center.x - horizontalRadius / 2
        val top = center.y - verticalRadius / 2
        val right = center.x + horizontalRadius / 2
        val bottom = center.y + verticalRadius / 2
        mPath.addArc(RectF(left, top, right, bottom), 0f, 360f)
    }

    private fun draw(paintBuilder: Paint.() -> Unit) {
        val properties = Paint()
        properties.strokeWidth = strokeSize
        properties.color = ColorConverter.convert(this@AndroidCanvasAdapter.color)
        properties.isAntiAlias = true

        paintBuilder(properties)

        mPath.close()
        canvas.drawPath(mPath, properties)
        mPath = Path()
    }
}
