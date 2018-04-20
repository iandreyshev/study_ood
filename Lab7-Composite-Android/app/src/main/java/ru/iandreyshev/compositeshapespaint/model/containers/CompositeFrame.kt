package ru.iandreyshev.compositeshapespaint.model.containers

class CompositeFrame(
        private val frames: InnerFramesIterator
) : AbstractFrame() {

    override val width: Float
        get() = frames.getSize({ position.x }, { width })

    override val height: Float
        get() = frames.getSize({ position.y }, { height })

    override var position: Vec2f
        get() = frames.getPosition()
        set(value) = frames.setPosition(value)

    override fun onResize(newWidth: Float, newHeight: Float) =
            frames.resize(newWidth, newHeight)

    interface InnerFramesIterator {
        fun forEach(action: AbstractFrame.() -> Unit)
    }

    private fun InnerFramesIterator.doActionOrFalse(action: AbstractFrame.() -> Unit): Boolean {
        var isEmpty = true
        forEach {
            isEmpty = false
            action(this)
        }
        return isEmpty
    }

    private fun InnerFramesIterator.getPosition(): Vec2f {
        var x = Float.MAX_VALUE
        var y = Float.MAX_VALUE
        val isEmpty = doActionOrFalse {
            if (position.x < x) {
                x = position.x
            }
            if (position.y < y) {
                y = position.y
            }
        }

        return if (isEmpty) Vec2f() else Vec2f(x, y)
    }

    private fun InnerFramesIterator.setPosition(newPosition: Vec2f) {
        val currPosition = getPosition()
        val offsetX = newPosition.x - currPosition.x
        val offsetY = newPosition.y - currPosition.y
        forEach {
            position = Vec2f(position.x + offsetX, position.y + offsetY)
        }
    }

    private fun InnerFramesIterator.getSize(
            toAxisPosition: AbstractFrame.() -> Float,
            toAxisSize: AbstractFrame.() -> Float): Float {
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

    private fun InnerFramesIterator.resize(newWidth: Float, newHeight: Float) {
        val widthBefore = this@CompositeFrame.width
        val heightBefore = this@CompositeFrame.height

        forEach {
            val xPositionProportion = 1f
            val yPositionProportion = 1f

            resize(
                    width / widthBefore * newWidth,
                    height / heightBefore * newHeight)

            position = Vec2f(
                    position.x * xPositionProportion,
                    position.y * yPositionProportion)
        }
    }
}
