package ru.iandreyshev.compositeshapespaint.model.shape.frame

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import junit.framework.TestCase
import org.junit.Test
import ru.iandreyshev.compositeshapespaint.model.container.Vec2f
import ru.iandreyshev.compositeshapespaint.model.extension.ISimpleIterator

class CompositeFrameTest {

    @Test
    fun canCalcPositionWithoutInnerShapes() {
        val frame = newCompositeFrame {}

        TestCase.assertEquals(0f, frame.position.x)
        TestCase.assertEquals(0f, frame.position.y)
    }

    @Test
    fun canCalcPositionWithOneInnerShape() {
        val frame = newCompositeFrame {
            subFrame {
                position { Vec2f(100, 900) }
            }
        }

        TestCase.assertEquals(100f, frame.position.x)
        TestCase.assertEquals(900f, frame.position.y)
    }

    @Test
    fun canCalcPositionWithSeveralInnerShapes() {
        val frame = newCompositeFrame {
            subFrame {
                position { Vec2f(10f, 10f) }
                width { 10f }
                height { 10f }
            }
            subFrame {
                position { Vec2f(15f, 15f) }
                width { 15f }
                height { 15f }
            }
        }

        TestCase.assertEquals(10f, frame.position.x)
        TestCase.assertEquals(10f, frame.position.y)
    }

    @Test
    fun canCalcSizeWithoutInnerShapes() {
        val frame = newCompositeFrame {}

        TestCase.assertEquals(0f, frame.width)
        TestCase.assertEquals(0f, frame.height)
    }

    @Test
    fun canCalcSizeWithOneInnerShape() {
        val frame = newCompositeFrame {
            subFrame {
                width { 5f }
                height { 15f }
            }
        }

        TestCase.assertEquals(5f, frame.width)
        TestCase.assertEquals(15f, frame.height)
    }

    @Test
    fun canCalcSizeWithSeveralInnerShapes() {
        val frame = newCompositeFrame {
            subFrame {
                position { Vec2f(10f, 10f) }
                width { 10f }
                height { 10f }
            }
            subFrame {
                position { Vec2f(15f, 15f) }
                width { 15f }
                height { 15f }
            }
        }

        TestCase.assertEquals(20f, frame.width)
        TestCase.assertEquals(20f, frame.height)
    }

    @Test
    fun canChangePositionOfInnerFrames() {
        var mockedSubFrame1: IFrame? = null
        val subFramePosition1 = Vec2f(10, 10)

        var mockedSubFrame2: IFrame? = null
        val subFramePosition2 = Vec2f(20, 20)

        val frame = newCompositeFrame {
            mockedSubFrame1 = mockSubFrame {
                whenever(this.position).thenReturn(subFramePosition1)
            }
            mockedSubFrame2 = mockSubFrame {
                whenever(this.position).thenReturn(subFramePosition2)
            }
        }

        frame.position = Vec2f(0, 0)

        verify(mockedSubFrame1)?.position = Vec2f(0, 0)
        verify(mockedSubFrame2)?.position = Vec2f(10, 10)
    }

    @Test
    fun canResizeInnerFrames() {
        var mockedSubFrame1: IFrame? = null
        val subFramePosition1 = Vec2f(50, 50)
        val subFrameWidth1 = 100f
        val subFrameHeight1 = 100f

        val frame = newCompositeFrame {
            mockedSubFrame1 = mockSubFrame {
                whenever(this.position).thenReturn(subFramePosition1)
                whenever(this.width).thenReturn(subFrameWidth1)
                whenever(this.height).thenReturn(subFrameHeight1)
            }
            subFrame {
                position { Vec2f(0, 0) }
                width { 200f }
                height { 200f }
            }
        }

        frame.resize(frame.width / 2, frame.height / 2)

        verify(mockedSubFrame1)?.position = Vec2f(subFramePosition1.x / 2, subFramePosition1.y / 2)
        verify(mockedSubFrame1)?.resize(subFrameWidth1 / 2, subFrameHeight1 / 2)
    }

    private fun newCompositeFrame(buildAction: CompositeFrameBuilder.() -> Unit): IFrame =
            CompositeFrameBuilder().apply(buildAction).build()

    private class CompositeFrameBuilder {
        private val subFrames = mutableListOf<IFrame>()

        fun subFrame(action: FrameBuilder.() -> Unit) =
                subFrames.add(FrameBuilder().apply(action).build())

        fun mockSubFrame(mockBuildAction: IFrame.() -> Unit): IFrame {
            val mockFrame: IFrame = mock()
            mockFrame.apply(mockBuildAction)
            subFrames.add(mockFrame)
            return mockFrame
        }

        fun build(): IFrame {
            return CompositeFrame(object : ISimpleIterator<IFrame> {
                override fun forEach(action: IFrame.() -> Unit) =
                        subFrames.forEach(action)
            })
        }
    }

    private class FrameBuilder {
        private var mGetPosition = { Vec2f() }
        private var mGetWidth = { 0f }
        private var mGetGetHeight = { 0f }

        fun position(getPosition: () -> Vec2f) {
            mGetPosition = getPosition
        }

        fun width(getWidth: () -> Float) {
            mGetWidth = getWidth
        }

        fun height(getHeight: () -> Float) {
            mGetGetHeight = getHeight
        }

        fun build(): IFrame =
                Frame(
                        position = mGetPosition(),
                        width = mGetWidth(),
                        height = mGetGetHeight()
                )
    }
}
