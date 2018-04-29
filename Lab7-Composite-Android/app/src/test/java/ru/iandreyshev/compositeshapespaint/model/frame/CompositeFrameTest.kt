package ru.iandreyshev.compositeshapespaint.model.frame

import junit.framework.TestCase.assertEquals
import org.junit.Test
import ru.iandreyshev.compositeshapespaint.model.container.Vec2f

class CompositeFrameTest {
    @Test
    fun canReturnZeroValuesIfNotContainShapes() {

    }

    @Test
    fun canCalcPositionWithOneInnerShape() {
        val frame = compositeFrame {
            subFrame {
                position { Vec2f(100, 900) }
            }
        }

        assertEquals(100f, frame.position.x)
        assertEquals(900f, frame.position.y)
    }

    @Test
    fun canCalcPositionWithSeveralInnerShapes() {
        val frame = compositeFrame {
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

        assertEquals(10f, frame.position.x)
        assertEquals(10f, frame.position.y)
    }

    @Test
    fun canCalcSizeWithSeveralInnerShapes() {
        val frame = compositeFrame {
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

        assertEquals(20f, frame.width)
        assertEquals(20f, frame.height)
    }

    @Test
    fun canCalcHeightWithSeveralInnerShapes() {

    }

    private fun compositeFrame(buildAction: CompositeFrameBuilder.() -> Unit): IFrame =
            CompositeFrameBuilder().apply(buildAction).build()

    private class CompositeFrameBuilder {
        private val subFrames = mutableListOf<IFrame>()

        fun subFrame(action: FrameBuilder.() -> Unit) =
                subFrames.add(FrameBuilder().apply(action).build())

        fun build(): IFrame {
            return CompositeFrame(object : CompositeFrame.InnerFramesIterator {
                override fun forEach(action: IFrame.() -> Unit) =
                        subFrames.forEach(action)
            })
        }
    }

    private class FrameBuilder {
        private var mGetPosition: (() -> Vec2f)? = null
        private var mGetWidth: (() -> Float)? = null
        private var mGetGetHeight: (() -> Float)? = null

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
                        mGetPosition?.invoke() ?: Vec2f(),
                        mGetWidth?.invoke() ?: 0f,
                        mGetGetHeight?.invoke() ?: 0f
                )
    }
}
