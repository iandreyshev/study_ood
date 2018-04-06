package modernGraphicsLib

import app.toRGBAColor
import com.nhaarman.mockito_kotlin.mock
import org.junit.Before
import org.junit.Test

class ModernGraphicsRendererTest {
    private lateinit var outputMock: (String) -> Unit
    private lateinit var renderer: ModernGraphicsRenderer

    @Before
    fun setup() {
        outputMock = mock()
        renderer = ModernGraphicsRenderer(outputMock)
    }

    @Test(expected = IllegalStateException::class)
    fun throwExceptionIfDrawLineBeforeBegin() {
        renderer.drawLine(ModernPoint(0, 0), ModernPoint(0, 0), 0.toRGBAColor())
    }
}
