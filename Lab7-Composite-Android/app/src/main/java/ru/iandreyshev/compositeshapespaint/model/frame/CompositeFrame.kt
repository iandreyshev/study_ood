package ru.iandreyshev.compositeshapespaint.model.frame

import ru.iandreyshev.compositeshapespaint.model.container.Vec2f

class CompositeFrame(
        private val frames: InnerFramesIterator
) : IFrame {

    override val width: Float
        get() = getSize(position.x, { position.x }, { width })

    override val height: Float
        get() = getSize(position.y, { position.y }, { height })

    override var position: Vec2f
        get() {
            var x = Float.MAX_VALUE
            var y = Float.MAX_VALUE
            val isNotEmpty = frames.doActionOrFalse {
                if (position.x < x) {
                    x = position.x
                }
                if (position.y < y) {
                    y = position.y
                }
            }

            return if (isNotEmpty) Vec2f(x, y) else Vec2f()
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

    private fun getSize(
            positionOnAxis: Float,
            getPositionOnAxis: IFrame.() -> Float,
            getSizeOnAxis: IFrame.() -> Float): Float {
        var max = Float.MIN_VALUE

        val isNotEmpty = frames.doActionOrFalse {
            val newMax = getPositionOnAxis(this) + getSizeOnAxis(this)
            if (newMax > max) {
                max = newMax
            }
        }

        return if (isNotEmpty) max - positionOnAxis else 0f
    }

    interface InnerFramesIterator {
        fun forEach(action: IFrame.() -> Unit)
    }

    private fun InnerFramesIterator.doActionOrFalse(action: IFrame.() -> Unit): Boolean {
        var isNotEmpty = false
        forEach {
            isNotEmpty = true
            this.apply(action)
        }
        return isNotEmpty
    }
}
