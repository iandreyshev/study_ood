package ru.iandreyshev.compositeshapespaint.ui.adapter

import android.graphics.*
import ru.iandreyshev.compositeshapespaint.model.canvas.Color
import ru.iandreyshev.compositeshapespaint.model.canvas.ICanvas
import ru.iandreyshev.compositeshapespaint.model.container.Vec2f
import ru.iandreyshev.compositeshapespaint.model.converter.ColorConverter

class AndroidCanvasAdapter : ICanvas {
    var canvas: Canvas? = null

    private var mPath = Path()

    private val mImageSrcRect = Rect()
    private val mImageDstRect = Rect()

    override var color: Color = Color.NONE

    override var strokeSize: Float = 0f

    override fun fill() {
        draw {
            style = Paint.Style.FILL
        }
    }

    override fun stroke() {
        draw {
            style = Paint.Style.STROKE
        }
    }

    override fun moveTo(position: Vec2f) {
        moveTo(position.x, position.y)
    }

    override fun moveTo(x: Float, y: Float) {
        mPath.moveTo(x, y)
    }

    override fun lineTo(position: Vec2f) {
        lineTo(position.x, position.y)
    }

    override fun lineTo(x: Float, y: Float) {
        mPath.lineTo(x, y)
    }

    override fun drawEllipse(centerX: Float, centerY: Float, horizontalRadius: Float, verticalRadius: Float) {
        val left = centerX - horizontalRadius
        val top = centerY - verticalRadius
        val right = centerX + horizontalRadius
        val bottom = centerY + verticalRadius
        mPath.addArc(RectF(left, top, right, bottom), 0f, 360f)
    }

    override fun drawImage(image: Bitmap, position: Vec2f, width: Float, height: Float) {
        mImageSrcRect.apply {
            top = 0
            left = 0
            right = image.width
            bottom = image.height
        }

        mImageDstRect.apply {
            left = position.x.toInt()
            top = position.y.toInt()
            right = position.x.toInt() + width.toInt()
            bottom = position.y.toInt() + height.toInt()
        }

        canvas?.drawBitmap(image, mImageSrcRect, mImageDstRect, Paint())
    }

    private fun draw(paintBuilder: Paint.() -> Unit) = canvas?.apply {
        val properties = Paint()
        properties.strokeWidth = strokeSize
        properties.color = ColorConverter.convert(this@AndroidCanvasAdapter.color)
        properties.isAntiAlias = true

        paintBuilder(properties)

        mPath.close()
        drawPath(mPath, properties)
        mPath = Path()
    }
}
