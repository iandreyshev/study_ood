package shape

import canvas.Color
import canvas.ICanvas
import com.nhaarman.mockito_kotlin.argThat
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import containers.Vec2i
import org.junit.Test

class RectangleTest {
    @Test
    fun canDrawItself() {
        val leftTop = Vec2i(-100, 100)
        val rightBottom = Vec2i(100, -100)
        val color = Color.PINK

        val canvas: ICanvas = mock()
        Rectangle(leftTop, rightBottom, color)
                .draw(canvas)

        verifyDrawingAtCanvas(canvas, leftTop, rightBottom, color)
    }

    @Test
    fun revertPointsInConstructor() {
        val leftTop = Vec2i(-100, 100)
        val rightBottom = Vec2i(100, -100)

        val canvas: ICanvas = mock()
        Rectangle(rightBottom, leftTop)
                .draw(canvas)

        verifyDrawingAtCanvas(canvas, leftTop, rightBottom, Color.BLACK)
    }

    @Test
    fun blackByDefault() {
        val canvas: ICanvas = mock()
        Rectangle(Vec2i(), Vec2i())
                .draw(canvas)

        verify(canvas).penColor = Color.BLACK
    }

    private fun verifyDrawingAtCanvas(canvas: ICanvas, leftTop: Vec2i, rightBottom: Vec2i, color: Color) {
        verify(canvas).penColor = color
        verify(canvas).drawLine(
                argThat { x == leftTop.x && y == leftTop.y },
                argThat { x == rightBottom.x && y == leftTop.y })
        verify(canvas).drawLine(
                argThat { x == rightBottom.x && y == leftTop.y },
                argThat { x == rightBottom.x && y == rightBottom.y })
        verify(canvas).drawLine(
                argThat { x == rightBottom.x && y == rightBottom.y },
                argThat { x == leftTop.x && y == rightBottom.y })
        verify(canvas).drawLine(
                argThat { x == leftTop.x && y == rightBottom.y },
                argThat { x == leftTop.x && y == leftTop.y })
    }
}
