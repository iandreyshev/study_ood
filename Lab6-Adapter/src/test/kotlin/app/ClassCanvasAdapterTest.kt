package app

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test

class ClassCanvasAdapterTest {
    private lateinit var outputMock: (String) -> Unit
    private lateinit var adapter: ClassCanvasAdapter

    @Before
    fun setup() {
        outputMock = mock()
        adapter = ClassCanvasAdapter(outputMock)
    }

    @Test(expected = IllegalStateException::class)
    fun throwExceptionIfStartDrawBeforeCallBegin() {
        adapter.lineTo(0, 0)
    }

    @Test(expected = IllegalStateException::class)
    fun throwExceptionAfterDoubleBeginDraw() {
        adapter.beginDraw()
        adapter.beginDraw()
    }

    @Test(expected = IllegalStateException::class)
    fun throwExceptionIfEndDrawBeforeBegin() {
        adapter.endDraw()
    }

    @Test
    fun canBeginDrawAndEndDraw() {
        adapter.beginDraw()
        verify(outputMock)("beginDraw")

        adapter.endDraw()
        verify(outputMock)("endDraw")
    }

    @Test
    fun canMovePenAfterMoveTo() {
        adapter.beginDraw()
        adapter.moveTo(1, 1)
        adapter.lineTo(2, 2)

        verify(outputMock)("beginDraw")
        verify(outputMock)("drawLine from ModernPoint(x=1, y=1) to ModernPoint(x=2, y=2)")
    }

    @Test
    fun canMovePenAfterLineTo() {
        adapter.beginDraw()
        adapter.lineTo(1, 1)
        adapter.lineTo(2, 2)

        verify(outputMock)("beginDraw")
        verify(outputMock)("drawLine from ModernPoint(x=0, y=0) to ModernPoint(x=1, y=1)")
        verify(outputMock)("drawLine from ModernPoint(x=1, y=1) to ModernPoint(x=2, y=2)")
    }
}
