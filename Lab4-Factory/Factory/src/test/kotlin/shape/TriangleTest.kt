package shape

import canvas.Color
import canvas.ICanvas
import com.nhaarman.mockito_kotlin.argThat
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import containers.Vec2i
import org.junit.Test

class TriangleTest {
    @Test
    fun canDrawItselfAtCanvas() {
        val point1 = Vec2i(123, 231)
        val point2 = Vec2i(495403, 5435)
        val point3 = Vec2i(1, 123)
        val color = Color.RED

        val canvas: ICanvas = mock()

        Triangle(point1, point2, point3, color)
                .draw(canvas)

        verify(canvas).penColor = color
        verify(canvas).drawLine(
                argThat { x == point1.x && y == point1.y },
                argThat { x == point2.x && y == point2.y })
        verify(canvas).drawLine(
                argThat { x == point2.x && y == point2.y },
                argThat { x == point3.x && y == point3.y })
        verify(canvas).drawLine(
                argThat { x == point3.x && y == point3.y },
                argThat { x == point1.x && y == point1.y })
    }

    @Test
    fun blackByDefault() {
        val canvas: ICanvas = mock()
        Triangle(Vec2i(), Vec2i(), Vec2i())
                .draw(canvas)

        verify(canvas).penColor = Color.BLACK
    }
}
