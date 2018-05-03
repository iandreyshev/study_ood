package ru.iandreyshev.compositeshapespaint.model.shape

import ru.iandreyshev.compositeshapespaint.model.canvas.ICanvas
import ru.iandreyshev.compositeshapespaint.model.shape.frame.IFrame
import ru.iandreyshev.compositeshapespaint.model.shape.frame.Frame
import ru.iandreyshev.compositeshapespaint.model.container.Vec2f
import ru.iandreyshev.compositeshapespaint.model.shape.style.IStyle

class Triangle(
        vertex1: Vec2f,
        vertex2: Vec2f,
        vertex3: Vec2f,
        style: IStyle,
        override val name: String = Triangle::class.java.simpleName
) : TermShape(style) {

    private lateinit var mVertex1Proportion: Vec2f
    private lateinit var mVertex2Proportion: Vec2f
    private lateinit var mVertex3Proportion: Vec2f

    // onDraw
    private val mPenPosition = Vec2f()
    // onDraw

    override val frame: IFrame by lazy {
        val minX = vertex1.x min vertex2.x min vertex3.x
        val maxX = vertex1.x max vertex2.x max vertex3.x
        val width = maxX - minX

        val minY = vertex1.y min vertex2.y min vertex3.y
        val maxY = vertex1.y max vertex2.y max vertex3.y
        val height = maxY - minY

        fun calcProportion(vertex: Vec2f): Vec2f =
                Vec2f((vertex.x - minX) / width, (vertex.y - minY) / height)

        mVertex1Proportion = calcProportion(vertex1)
        mVertex2Proportion = calcProportion(vertex2)
        mVertex3Proportion = calcProportion(vertex3)

        return@lazy Frame(Vec2f(minX, minY), width, height)
    }

    override fun onDrawShape(canvas: ICanvas) =
            onDraw(canvas)

    override fun onDrawStroke(canvas: ICanvas) =
            onDraw(canvas)

    private fun onDraw(canvas: ICanvas) {
        fun calcPosition(vertexProperty: Vec2f) {
            mPenPosition.x = frame.position.x + frame.width * vertexProperty.x
            mPenPosition.y =frame.position.y + frame.height * vertexProperty.y
        }

        frame.position

        calcPosition(mVertex1Proportion)
        canvas.moveTo(mPenPosition)

        calcPosition(mVertex2Proportion)
        canvas.lineTo(mPenPosition)

        calcPosition(mVertex3Proportion)
        canvas.lineTo(mPenPosition)

        calcPosition(mVertex1Proportion)
        canvas.lineTo(mPenPosition)
    }

    private infix fun Float.min(other: Float): Float = Math.min(this, other)
    private infix fun Float.max(other: Float): Float = Math.max(this, other)
}
