package shape

import canvas.Color
import canvas.ICanvas
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import containers.Vec2i
import org.junit.Test
import java.lang.IllegalArgumentException

class EllipseTest {
    @Test(expected = IllegalArgumentException::class)
    fun throwExceptionIfCreateEllipseWithNegativeHorizontalRadius() {
        Ellipse(Vec2i(), -1, 0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun throwExceptionIfCreateEllipseWithNegativeVerticalRadius() {
        Ellipse(Vec2i(), 0, -1)
    }

    @Test
    fun canCreateEllipseWithZeroRadius() {
        Ellipse(Vec2i(), 0, 0)
    }

    @Test
    fun callDrawEllipseAtCanvas() {
        val center = Vec2i(1, 0)
        val horizontalRadius = 321
        val verticalRadius = 123
        val color = Color.PINK

        val canvas: ICanvas = mock()
        Ellipse(center, horizontalRadius, verticalRadius, color)
                .draw(canvas)

        verify(canvas).penColor = color
        verify(canvas).drawEllipse(center, horizontalRadius, verticalRadius)
    }

    @Test
    fun blackByDefault() {
        val canvas: ICanvas = mock()
        Ellipse(Vec2i(), 0, 0)
                .draw(canvas)

        verify(canvas).penColor = Color.BLACK
    }
}
