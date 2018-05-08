package ru.iandreyshev.adobekiller.ui.adapter

import android.graphics.*
import ru.iandreyshev.adobekiller.model.canvas.Color
import ru.iandreyshev.adobekiller.model.canvas.ICanvas
import ru.iandreyshev.adobekiller.model.container.Vec2f
import ru.iandreyshev.adobekiller.model.converter.ColorConverter

class AndroidCanvasAdapter : ICanvas {
    var canvas: Canvas? = null

    override var color: Color = Color.NONE

    override var strokeSize: Float = 0f

    // onDraw
    private val mPath = Path()
    private val mPaintProperties = Paint()
    private val mImageSrcRect = Rect()
    private val mImageDstRect = Rect()
    private val mEllipseRect = RectF()
    // onDraw

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
        mEllipseRect.apply {
            left = centerX - horizontalRadius
            top = centerY - verticalRadius
            right = centerX + horizontalRadius
            bottom = centerY + verticalRadius
        }
        mPath.addArc(mEllipseRect, 0f, 360f)
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
        mPaintProperties.strokeWidth = strokeSize
        mPaintProperties.color = ColorConverter.convert(this@AndroidCanvasAdapter.color)
        mPaintProperties.isAntiAlias = true

        paintBuilder(mPaintProperties)

        mPath.close()
        drawPath(mPath, mPaintProperties)

        mPaintProperties.reset()
        mPath.reset()
    }
}
