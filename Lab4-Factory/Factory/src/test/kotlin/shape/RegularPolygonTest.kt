package shape

import canvas.Color
import canvas.ICanvas
import com.nhaarman.mockito_kotlin.argThat
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import containers.Vec2i
import org.junit.Test

class RegularPolygonTest {
    @Test(expected = IllegalArgumentException::class)
    fun throwExceptionIfCreateWithVertexCountLessThanMinimum() {
        RegularPolygon(Vec2i(), RegularPolygon.MIN_VERTEX - 1, 0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun throwExceptionIfCreateWithNegativeRadius() {
        RegularPolygon(Vec2i(), RegularPolygon.MIN_VERTEX, -1)
    }

    @Test
    fun canDrawItselfAtCanvas() {
        val radius = 10
        val color = Color.GREEN

        val canvas: ICanvas = mock()
        RegularPolygon(Vec2i(), 4, radius, color)
                .draw(canvas)

        verify(canvas).penColor = color
        verify(canvas).drawLine(
                argThat { x == 10 && y == 0 },
                argThat { x == 0 && y == 10 })
        verify(canvas).drawLine(
                argThat { x == 0 && y == 10 },
                argThat { x == -10 && y == 0 })
        verify(canvas).drawLine(
                argThat { x == -10 && y == 0 },
                argThat { x == 0 && y == -10 })
        verify(canvas).drawLine(
                argThat { x == 0 && y == -10 },
                argThat { x == 10 && y == 0 })
    }

    @Test
    fun blackByDefault() {
        val canvas: ICanvas = mock()
        RegularPolygon(Vec2i(), RegularPolygon.MIN_VERTEX, 0)
                .draw(canvas)

        verify(canvas).penColor = Color.BLACK
    }
}
