package ru.iandreyshev.compositeshapespaint.model.shape.style

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import junit.framework.TestCase.assertEquals
import org.junit.Test
import ru.iandreyshev.compositeshapespaint.model.canvas.Color
import ru.iandreyshev.compositeshapespaint.model.extension.ISimpleIterator

class CompositeStyleTest {

    @Test
    fun canChangeFillColorOfSeveralInnerStyles() {
        var innerStyleMock1: IStyle? = null
        var innerStyleMock2: IStyle? = null

        val compositeStyle = newCompositeStyle {
            innerStyleMock1 = subStyleMock()
            innerStyleMock2 = subStyleMock()
        }

        val newFillColor = Color.RED
        compositeStyle.setFillColor(newFillColor)

        verify(innerStyleMock1)?.setFillColor(newFillColor)
        verifyNoMoreInteractions(innerStyleMock1)

        verify(innerStyleMock2)?.setFillColor(newFillColor)
        verifyNoMoreInteractions(innerStyleMock2)
    }

    @Test
    fun canChangeStrokeColorOfSeveralInnerStyles() {
        var innerStyleMock1: IStyle? = null
        var innerStyleMock2: IStyle? = null

        val compositeStyle = newCompositeStyle {
            innerStyleMock1 = subStyleMock()
            innerStyleMock2 = subStyleMock()
        }

        val newStrokeColor = Color.RED
        compositeStyle.setStrokeColor(newStrokeColor)

        verify(innerStyleMock1)?.setStrokeColor(newStrokeColor)
        verifyNoMoreInteractions(innerStyleMock1)

        verify(innerStyleMock2)?.setStrokeColor(newStrokeColor)
        verifyNoMoreInteractions(innerStyleMock2)
    }

    @Test
    fun canChangeStrokeSizeOfSeveralInnerStyles() {
        var innerStyleMock1: IStyle? = null
        var innerStyleMock2: IStyle? = null

        val compositeStyle = newCompositeStyle {
            innerStyleMock1 = subStyleMock()
            innerStyleMock2 = subStyleMock()
        }

        val newStrokeSize = 133f
        compositeStyle.setStrokeSize(newStrokeSize)

        verify(innerStyleMock1)?.setStrokeSize(newStrokeSize)
        verifyNoMoreInteractions(innerStyleMock1)

        verify(innerStyleMock2)?.setStrokeSize(newStrokeSize)
        verifyNoMoreInteractions(innerStyleMock2)
    }

    @Test
    fun returnNullIfInnerStylesHaveDifferentFillColor() {
        val compositeStyle = newCompositeStyle {
            subStyleMock {
                whenever(getFillColor()).thenReturn(Color.RED)
            }
            subStyleMock {
                whenever(getFillColor()).thenReturn(Color.BLACK)
            }
        }

        assertEquals(null, compositeStyle.getFillColor())
    }

    @Test
    fun returnNullIfInnerStylesHaveDifferentStrokeColor() {
        val compositeStyle = newCompositeStyle {
            subStyleMock {
                whenever(getStrokeColor()).thenReturn(Color.RED)
            }
            subStyleMock {
                whenever(getStrokeColor()).thenReturn(Color.BLACK)
            }
        }

        assertEquals(null, compositeStyle.getStrokeColor())
    }

    @Test
    fun returnNullIfInnerStylesHaveDifferentStrokeSizeColor() {
        val compositeStyle = newCompositeStyle {
            subStyleMock {
                whenever(getStrokeSize()).thenReturn(2f)
            }
            subStyleMock {
                whenever(getStrokeSize()).thenReturn(4f)
            }
        }

        assertEquals(null, compositeStyle.getStrokeSize())
    }

    private fun newCompositeStyle(buildAction: CompositeStyleBuilder.() -> Unit): IStyle =
            CompositeStyleBuilder().apply(buildAction).build()

    private class CompositeStyleBuilder {
        private val mSubStyles = mutableListOf<IStyle>()

        fun subStyleMock(buildAction: IStyle.() -> Unit = {}): IStyle {
            val styleMock: IStyle = mock()
            styleMock.apply(buildAction)
            mSubStyles.add(styleMock)
            return styleMock
        }

        fun build(): IStyle =
                CompositeStyle(object : ISimpleIterator<IStyle> {
                    override fun forEach(action: (IStyle) -> Unit) =
                            mSubStyles.forEach { action(it) }
                })
    }

    private class StyleBuilder {
        private var mGetFillColor = { Color.NONE }
        private var mGetStrokeColor = { Color.NONE }
        private var mGetStrokeSize = { 0f }

        fun fillColor(getColor: () -> Color) {
            mGetFillColor = getColor
        }

        fun strokeColor(getColor: () -> Color) {
            mGetStrokeColor = getColor
        }

        fun strokeSize(getSize: () -> Float) {
            mGetStrokeSize = getSize
        }

        fun build(): IStyle =
                Style(
                        fillColor = mGetFillColor(),
                        strokeColor = mGetStrokeColor(),
                        strokeSize = mGetStrokeSize()
                )
    }
}
