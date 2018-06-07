package ru.iandreyshev.adobeKiller.presentation.drawing.drawable

import ru.iandreyshev.adobeKiller.presentation.drawing.ICanvas
import ru.iandreyshev.geometry.vector.Vec2f
import ru.iandreyshev.geometry.frame.Frame
import ru.iandreyshev.canvas.style.Style

class TriangleView(
        override val frame: Frame,
        override val style: Style
) : DrawableView() {

    // onDraw
    private val mPenPosition = Vec2f()
    // onDraw

    override fun onDrawShape(canvas: ICanvas) =
            onDraw(canvas)

    override fun onDrawStroke(canvas: ICanvas) =
            onDraw(canvas)

    private fun onDraw(canvas: ICanvas) {
        mPenPosition.x = frame.x + frame.width / 2
        mPenPosition.y = frame.y
        canvas.moveTo(mPenPosition)

        mPenPosition.x = frame.x + frame.width
        mPenPosition.y = frame.y + frame.height
        canvas.lineTo(mPenPosition)

        mPenPosition.x = frame.x
        canvas.lineTo(mPenPosition)

        mPenPosition.x = frame.x + frame.width / 2
        mPenPosition.y = frame.y
        canvas.lineTo(mPenPosition)
    }

    override fun hitTest(testX: Float, testY: Float): Boolean {
        val pA = Vec2f(frame.position.x + frame.width / 2, frame.position.y)
        val pB = Vec2f(frame.position.x, frame.position.y + height)
        val pC = Vec2f(frame.position.x + width, frame.position.y + height)

        val planeAB = (pA.x - testX) * (pB.y - testY) - (pB.x - testX) * (pA.y - testY)
        val planeBC = (pB.x - testX) * (pC.y - testY) - (pC.x - testX) * (pB.y - testY)
        val planeCA = (pC.x - testX) * (pA.y - testY) - (pA.x - testX) * (pC.y - testY)

        return sign(planeAB) == sign(planeBC) && sign(planeBC) == sign(planeCA)
    }

    private fun sign(n: Float): Float {
        return Math.abs(n) / n
    }

}
