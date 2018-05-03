package ru.iandreyshev.compositeshapespaint.ui.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import ru.iandreyshev.compositeshapespaint.model.container.Vec2f
import ru.iandreyshev.compositeshapespaint.model.shape.frame.*
import ru.iandreyshev.compositeshapespaint.ui.OnTouchMoveCallback
import ru.iandreyshev.compositeshapespaint.ui.shape.TargetFrameHelper

class TargetedCanvasView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : CanvasView(context, attrs, defStyleAttr) {

    companion object {
        private const val RECT_STROKE_WIDTH = 3f
        private const val CIRCLE_RADIUS = 20f
        private const val CIRCLE_TOUCH_RADIUS = 50f
        private const val MIN_WIDTH = 42f
        private const val MIN_HEIGHT = 42f
    }

    override var onMove: OnTouchMoveCallback? = null
        set(value) {
            field = { lastX, lastY, newX, newY ->
                mTargetFrameHelper.handleMoveEvent(lastX, lastY, newX, newY)
                value?.invoke(lastX, lastY, newX, newY)
            }
        }

    private val mTargetFrameHelper = TargetFrameHelper(
            MIN_WIDTH,
            MIN_HEIGHT,
            CIRCLE_TOUCH_RADIUS
    )

    private val mRectPath = Path()
    private val mRectProperties = Paint().apply {
        strokeWidth = RECT_STROKE_WIDTH
        color = Color.DKGRAY
        style = Paint.Style.STROKE
        pathEffect = DashPathEffect(floatArrayOf(30f, 10f), 0f)
    }

    private val mCirclesPath = Path()
    private val mCircleProperties = Paint().apply {
        style = Paint.Style.FILL
        color = Color.GRAY
    }

    init {
        onMove = { _, _, _, _ -> } // To create start callback
    }

    fun onTargetChanged(callback: (IFrame) -> Unit) {
        mTargetFrameHelper.onFrameChanged { newFrame ->
            prepareDrawingProperties(newFrame)
            callback(newFrame)
            invalidate()
        }
    }

    fun setTarget(frame: IFrame?) {
        if (frame == null) {
            mTargetFrameHelper.target = null
            isEnabled = false
            return
        }

        isEnabled = true
        mTargetFrameHelper.target = Frame(frame.position, frame.width, frame.height).apply {
            prepareDrawingProperties(this)
        }
        invalidate()
    }

    fun hitTest(x: Float, y: Float): Boolean =
            mTargetFrameHelper.target.let {
                it ?: return false
                return@let it.hitTest(x, y)
            }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (isEnabled) {
            canvas.drawPath(mRectPath, mRectProperties)
            canvas.drawPath(mCirclesPath, mCircleProperties)
        }
    }

    private fun prepareDrawingProperties(frame: IFrame) {
        val leftTop = frame.position
        val rightTop = Vec2f(frame.position.x + frame.width, frame.position.y)
        val rightBottom = Vec2f(frame.position.x + frame.width, frame.position.y + frame.height)
        val leftBottom = Vec2f(frame.position.x, frame.position.y + frame.height)

        mRectPath.apply {
            reset()
            moveTo(leftTop.x, leftTop.y)
            lineTo(rightTop.x, rightTop.y)
            lineTo(rightBottom.x, rightBottom.y)
            lineTo(leftBottom.x, leftBottom.y)
            lineTo(leftTop.x, leftTop.y)
            close()
        }

        mCirclesPath.apply {
            reset()
            addCircle(leftTop.x, leftTop.y, CIRCLE_RADIUS, Path.Direction.CW)
            addCircle(rightTop.x, rightTop.y, CIRCLE_RADIUS, Path.Direction.CW)
            addCircle(rightBottom.x, rightBottom.y, CIRCLE_RADIUS, Path.Direction.CW)
            addCircle(leftBottom.x, leftBottom.y, CIRCLE_RADIUS, Path.Direction.CW)
            close()
        }
    }
}
