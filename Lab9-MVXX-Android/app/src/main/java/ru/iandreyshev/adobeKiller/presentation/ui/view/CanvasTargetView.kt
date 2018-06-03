package ru.iandreyshev.adobeKiller.presentation.ui.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import ru.iandreyshev.adobeKiller.presentation.drawing.container.Vec2f
import ru.iandreyshev.adobeKiller.presentation.drawing.extension.hitTest
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.IConstFrame
import ru.iandreyshev.adobeKiller.presentation.ui.OnTouchCallback
import ru.iandreyshev.adobeKiller.presentation.ui.OnTouchFinishCallback
import ru.iandreyshev.adobeKiller.presentation.ui.targetFrame.TargetFrameHelper

class CanvasTargetView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : CanvasView(context, attrs, defStyleAttr) {

    companion object {
        private const val RECT_STROKE_WIDTH = 3f
        private const val CIRCLE_RADIUS = 20f
        private const val CIRCLE_TOUCH_RADIUS = 72f
        private const val MIN_WIDTH = 100f
        private const val MIN_HEIGHT = 100f
    }

    private var mPointerId: Int? = null
    private var mOnTouchStartCallback: OnTouchCallback? = null
    private var mOnTouchFinishCallback: OnTouchFinishCallback? = null
    private var mIsTargetChanged: Boolean = false
    private val mTargetFrameHelper = TargetFrameHelper(
            minWidth = MIN_WIDTH,
            minHeight = MIN_HEIGHT,
            circleTouchRadius = CIRCLE_TOUCH_RADIUS
    )

    // onDraw
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
    // onDraw

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event ?: return super.onTouchEvent(event)

        val pointerId = event.getPointerId(event.actionIndex)

        when (event.action) {
            MotionEvent.ACTION_DOWN -> { // first touch
                mPointerId = pointerId
                mOnTouchStartCallback?.invoke(event.x, event.y)
                return true
            }

            MotionEvent.ACTION_MOVE -> { // move
                if (pointerId != mPointerId || event.historySize < 1) {
                    return false
                }

                val lastX = event.getHistoricalX(0)
                val lastY = event.getHistoricalY(0)
                val isTargetChanged = mTargetFrameHelper.handleMoveEvent(lastX, lastY, event.x, event.y)

                if (!mIsTargetChanged) {
                    mIsTargetChanged = isTargetChanged
                }

                return true
            }

            MotionEvent.ACTION_UP -> { // finish touch
                mOnTouchFinishCallback?.invoke(mIsTargetChanged)
                return true
            }
        }

        return super.onTouchEvent(event)
    }

    fun onTouchStart(callback: OnTouchCallback?) {
        mOnTouchStartCallback = callback
    }

    fun onTouchFinish(callback: OnTouchFinishCallback?) {
        mOnTouchFinishCallback = callback
    }

    fun onTargetFrameChanged(callback: (Frame) -> Unit) {
        mTargetFrameHelper.onFrameChanged { newFrame ->
            prepareDrawingProperties(newFrame)
            callback(newFrame)
            invalidate()
        }
    }

    fun setTarget(frame: IConstFrame?) {
        if (frame == null) {
            mTargetFrameHelper.target = null
            isEnabled = false
            return
        }

        isEnabled = true
        mTargetFrameHelper.target = frame.apply {
            prepareDrawingProperties(this)
        }
        invalidate()
    }

    fun hitTest(x: Float, y: Float): Boolean {
        return mTargetFrameHelper.target.let {
            it ?: return false
            return@let it.hitTest(x, y)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (isEnabled) {
            canvas.drawPath(mRectPath, mRectProperties)
            canvas.drawPath(mCirclesPath, mCircleProperties)
        }
    }

    private fun prepareDrawingProperties(frame: IConstFrame) {
        val rightTop = Vec2f(frame.x + frame.width, frame.y)
        val rightBottom = Vec2f(frame.x + frame.width, frame.y + frame.height)
        val leftBottom = Vec2f(frame.x, frame.y + frame.height)

        mRectPath.apply {
            reset()
            moveTo(frame.x, frame.y)
            lineTo(rightTop.x, rightTop.y)
            lineTo(rightBottom.x, rightBottom.y)
            lineTo(leftBottom.x, leftBottom.y)
            lineTo(frame.x, frame.y)
            close()
        }

        mCirclesPath.apply {
            reset()
            addCircle(frame.x, frame.y, CIRCLE_RADIUS, Path.Direction.CW)
            addCircle(rightTop.x, rightTop.y, CIRCLE_RADIUS, Path.Direction.CW)
            addCircle(rightBottom.x, rightBottom.y, CIRCLE_RADIUS, Path.Direction.CW)
            addCircle(leftBottom.x, leftBottom.y, CIRCLE_RADIUS, Path.Direction.CW)
            close()
        }
    }
}
