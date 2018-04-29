package ru.iandreyshev.compositeshapespaint.ui.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import ru.iandreyshev.compositeshapespaint.model.container.Vec2f
import ru.iandreyshev.compositeshapespaint.model.frame.*
import ru.iandreyshev.compositeshapespaint.ui.OnTouchMoveCallback

class TargetedCanvasView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : CanvasView(context, attrs, defStyleAttr) {

    companion object {
        private const val RECT_STROKE_WIDTH = 6f
        private const val CIRCLE_RADIUS = 20f
        private const val CIRCLE_TOUCH_RADIUS = 40f
        private const val MIN_WIDTH = 42f
        private const val MIN_HEIGHT = 42f
    }

    override var onMove: OnTouchMoveCallback? = null
        set(value) {
            field = { lastX, lastY, newX, newY ->
                mTargetFrame?.let { targetFrame ->
                    handleOnMoveEvent(targetFrame, lastX, lastY, newX, newY)?.let { newFrame ->
                        mTargetFrame = newFrame
                        prepareDrawingProperties(newFrame)
                        mOnChangeCallback?.invoke(newFrame)
                        invalidate()
                    }
                }
                value?.invoke(lastX, lastY, newX, newY)
            }
        }

    private var mTargetFrame: IFrame? = null
    private var mOnChangeCallback: ((IFrame) -> Unit)? = null

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
        onMove = { _, _, _, _ -> } // To create callback
    }

    fun onChangeTarget(callback: (IFrame) -> Unit) {
        mOnChangeCallback = callback
    }

    fun setTarget(position: Vec2f, width: Float, height: Float) {
        isEnabled = true
        mTargetFrame = Frame(position, width, height).apply {
            prepareDrawingProperties(this)
            invalidate()
        }
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

    private fun handleOnMoveEvent(frame: IFrame, lastX: Float?, lastY: Float?, newX: Float, newY: Float): IFrame? {
        lastX ?: return null
        lastY ?: return null

        return handleCircleMove(frame, lastX, lastY, newX, newY) ?: kotlin.run {
            handleRectMove(frame, lastX, lastY, newX, newY)
        }
    }

    private fun handleCircleMove(frame: IFrame, lastX: Float, lastY: Float, newX: Float, newY: Float): IFrame? {
        fun hitTest(circleX: Float, circleY: Float): Boolean {
            val xRange = (circleX - CIRCLE_TOUCH_RADIUS..circleX + CIRCLE_TOUCH_RADIUS)
            val yRange = (circleY - CIRCLE_TOUCH_RADIUS..circleY + CIRCLE_TOUCH_RADIUS)

            return xRange.contains(lastX) && yRange.contains(lastY) ||
                    xRange.contains(newX) && yRange.contains(newY)
        }

        val position = frame.position

        return when {
            hitTest(frame.left, frame.top) -> {
                // Left top
                if (newX > frame.right - MIN_WIDTH) return null
                if (newY > frame.bottom - MIN_HEIGHT) return null

                val newWidth = frame.right - newX
                val newHeight = frame.bottom - newY

                return Frame(Vec2f(newX, newY), newWidth, newHeight)
            }
            hitTest(frame.right, frame.top) -> {
                // Right top
                if (newX < frame.left + MIN_WIDTH) return null
                if (newY > frame.bottom - MIN_HEIGHT) return null

                val newWidth = newX - frame.left
                val newHeight = frame.bottom - newY

                return Frame(Vec2f(frame.left, newY), newWidth, newHeight)
            }
            hitTest(frame.right, frame.bottom) -> {
                // Right bottom
                if (newX < frame.left + MIN_WIDTH) return null
                if (newY < frame.top + MIN_HEIGHT) return null

                val newWidth = newX - frame.left
                val newHeight = newY - frame.top

                return Frame(position, newWidth, newHeight)
            }
            hitTest(frame.left, frame.bottom) -> {
                // Left bottom
                if (newX > frame.right - MIN_WIDTH) return null
                if (newY < frame.top + MIN_HEIGHT) return null

                val newWidth = frame.right - newX
                val newHeight = newY - frame.top

                return Frame(Vec2f(newX, frame.top), newWidth, newHeight)
            }
            else -> null
        }
    }

    private fun handleRectMove(frame: IFrame, lastX: Float, lastY: Float, newX: Float, newY: Float): IFrame? {
        if (!frame.hitTest(lastX, lastY)) {
            return mTargetFrame
        }

        val x = newX - frame.width / 2
        val y = newY - frame.height / 2

        return Frame(Vec2f(x, y), frame.width, frame.height)
    }
}
