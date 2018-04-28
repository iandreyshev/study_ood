package ru.iandreyshev.compositeshapespaint.model.frame

import ru.iandreyshev.compositeshapespaint.model.container.Vec2f

class CompositeFrame(
        private val frames: InnerFramesIterator
) : IFrame {

    override val width: Float
        get() = frames.getSize({ position.x }, { width })

    override val height: Float
        get() = frames.getSize({ position.y }, { height })

    override var position: Vec2f
        get() {
            var x = Float.MAX_VALUE
            var y = Float.MAX_VALUE
            val isEmpty = frames.doActionOrFalse {
                if (position.x < x) {
                    x = position.x
                }
                if (position.y < y) {
                    y = position.y
                }
            }

            return if (isEmpty) Vec2f() else Vec2f(x, y)
        }
        set(value) {
            val currPosition = position
            val offsetX = value.x - currPosition.x
            val offsetY = value.y - currPosition.y

            frames.forEach {
                position = Vec2f(position.x + offsetX, position.y + offsetY)
            }
        }

    override fun resize(newWidth: Float, newHeight: Float) = frames.forEach {
        val widthBefore = this@CompositeFrame.width
        val heightBefore = this@CompositeFrame.height

        frames.forEach {
            val xPositionProportion = 1f
            val yPositionProportion = 1f

            resize(newWidth = width / widthBefore * newWidth,
                    newHeight = height / heightBefore * newHeight)

            position = Vec2f(x = position.x * xPositionProportion,
                    y = position.y * yPositionProportion)
        }
    }

    interface InnerFramesIterator {
        fun forEach(action: IFrame.() -> Unit)
    }

    private fun InnerFramesIterator.doActionOrFalse(action: IFrame.() -> Unit): Boolean {
        var isEmpty = true
        forEach {
            isEmpty = false
            action(this)
        }
        return isEmpty
    }

    private fun InnerFramesIterator.getSize(
            toAxisPosition: IFrame.() -> Float,
            toAxisSize: IFrame.() -> Float): Float {
        var min = Float.MAX_VALUE
        var max = Float.MIN_VALUE
        val isEmpty = doActionOrFalse {
            val axisPos = toAxisPosition(this)
            if (axisPos < min) {
                min = axisPos
            }
            val newMaxX = min + toAxisSize(this)
            if (newMaxX > max) {
                max = newMaxX
            }
        }

        return if (isEmpty) 0f else max - min
    }
}
