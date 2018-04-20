package ru.iandreyshev.compositeshapespaint.model.shape

import canvas.Color
import ru.iandreyshev.compositeshapespaint.model.canvas.ICanvas
import ru.iandreyshev.compositeshapespaint.model.containers.AbstractFrame
import ru.iandreyshev.compositeshapespaint.model.containers.Frame
import ru.iandreyshev.compositeshapespaint.model.containers.Vec2f

class Triangle(
        vertex1: Vec2f,
        vertex2: Vec2f,
        vertex3: Vec2f,
        strokeSize: Float = 5f,
        fillColor: Color = Color.BLACK,
        strokeColor: Color = Color.WHITE,
        override val name: String = Ellipse::class.java.simpleName
) : TermShape(strokeSize, fillColor, strokeColor) {
    private lateinit var mVertex1Property: Vec2f
    private lateinit var mVertex2Property: Vec2f
    private lateinit var mVertex3Property: Vec2f

    override val frame: AbstractFrame by lazy {
        val minX = vertex1.x min vertex2.x min vertex3.x
        val maxX = vertex1.x max vertex2.x max vertex3.x
        val width = maxX - minX

        val minY = vertex1.y min vertex2.y min vertex3.y
        val maxY = vertex1.y max vertex2.y max vertex3.y
        val height = maxY - minY

        mVertex1Property = Vec2f(vertex1.x - minX / width, vertex1.y - minY / height)
        mVertex2Property = Vec2f(vertex2.x - minX / width, vertex2.y - minY / height)
        mVertex3Property = Vec2f(vertex3.x - minX / width, vertex3.y - minY / height)

        return@lazy Frame(Vec2f(minX, minY), width, height)
    }

    override fun onDrawShape(canvas: ICanvas) = onDraw(canvas)

    override fun onDrawStroke(canvas: ICanvas) = onDraw(canvas)

    private fun onDraw(canvas: ICanvas) {
        fun positionForVertex(vertexProperty: Vec2f): Vec2f =
                Vec2f(
                        frame.position.x + frame.width * vertexProperty.x,
                        frame.position.y + frame.height * vertexProperty.y
                )

        val v1Position = positionForVertex(mVertex1Property)
        val v2Position = positionForVertex(mVertex2Property)
        val v3Position = positionForVertex(mVertex3Property)

        canvas.moveTo(v1Position)
        canvas.lineTo(v2Position)
        canvas.lineTo(v3Position)
        canvas.lineTo(v1Position)
    }

    private infix fun Float.min(other: Float): Float = Math.min(this, other)
    private infix fun Float.max(other: Float): Float = Math.min(this, other)
}
