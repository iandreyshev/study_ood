package ru.iandreyshev.compositeshapespaint.model.shape

import ru.iandreyshev.compositeshapespaint.model.canvas.ICanvas
import ru.iandreyshev.compositeshapespaint.model.shape.frame.IFrame
import ru.iandreyshev.compositeshapespaint.model.shape.frame.Frame
import ru.iandreyshev.compositeshapespaint.model.container.Vec2f
import ru.iandreyshev.compositeshapespaint.model.shape.style.IStyle

class RegularPolygon(
        center: Vec2f,
        private val vertexCount: Int,
        radius: Float,
        style: IStyle,
        override val name: String = "Regular polygon"
) : TermShape(style) {

    companion object {
        const val MIN_VERTEX = 3
    }

    // onDraw
    private val mAngleIncrease = 2 * Math.PI / vertexCount
    private val mXPoints = FloatArray(vertexCount)
    private val mYPoints = FloatArray(vertexCount)
    private val mPenPosition = Vec2f()
    // onDraw

    init {
        if (vertexCount < MIN_VERTEX) throw IllegalArgumentException("Min vertex count is $MIN_VERTEX")
        if (radius < 0) throw IllegalArgumentException("Radius can not be negative")
    }

    override val frame: IFrame by lazy {
        val position = Vec2f(center.x - radius, center.y - radius)
        return@lazy Frame(position, radius * 2, radius * 2)
    }

    override fun onDrawShape(canvas: ICanvas) =
            onDraw(canvas)

    override fun onDrawStroke(canvas: ICanvas) =
            onDraw(canvas)

    private fun onDraw(canvas: ICanvas) {
        var angle = .0

        repeat(vertexCount) {
            val centerX = frame.position.x + frame.width / 2
            val centerY = frame.position.y + frame.height / 2

            mXPoints[it] = Math.round(frame.radius * Math.cos(angle)) + centerX
            mYPoints[it] = Math.round(frame.radius * Math.sin(angle)) + centerY
            angle += mAngleIncrease
        }

        mPenPosition.x = mXPoints.first()
        mPenPosition.y = mYPoints.first()

        if (!mXPoints.isEmpty() && !mYPoints.isEmpty()) {
            canvas.moveTo(mPenPosition)
        }

        repeat(vertexCount) { pointIndex ->
            val pointNum = if (pointIndex + 1 == vertexCount) 0 else pointIndex + 1
            mPenPosition.x = mXPoints[pointNum]
            mPenPosition.y = mYPoints[pointNum]
            canvas.lineTo(mPenPosition)
        }
    }

    private val IFrame.radius: Float
        get() = Math.min(width, height) / 2
}
