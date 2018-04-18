package containers

class CompositeFrame(private val frames: InnerFramesIterator) : AbstractFrame() {
    override val width: Int
        get() = frames.getSize({ position.x }, { width })

    override val height: Int
        get() = frames.getSize({ position.y }, { height })

    override var position: Vec2i
        get() = frames.getPosition()
        set(value) = frames.setPosition(value)

    override fun onResize(newWidth: Int, newHeight: Int) =
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

    private fun InnerFramesIterator.getPosition(): Vec2i {
        var x = Int.MAX_VALUE
        var y = Int.MAX_VALUE
        val isEmpty = doActionOrFalse {
            if (position.x < x) {
                x = position.x
            }
            if (position.y < y) {
                y = position.y
            }
        }

        return if (isEmpty) Vec2i() else Vec2i(x, y)
    }

    private fun InnerFramesIterator.setPosition(newPosition: Vec2i) {
        val currPosition = getPosition()
        val offsetX = newPosition.x - currPosition.x
        val offsetY = newPosition.y - currPosition.y
        forEach { position -= Vec2i(offsetX, offsetY) }
    }

    private fun InnerFramesIterator.getSize(
            toAxisPosition: AbstractFrame.() -> Int,
            toAxisSize: AbstractFrame.() -> Int): Int {
        var min = Int.MAX_VALUE
        var max = Int.MIN_VALUE
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

        return if (isEmpty) 0 else max - min
    }

    private fun InnerFramesIterator.resize(newWidth: Int, newHeight: Int) {
        val xFactor = width.toFloat() / newWidth
        val yFactor = height.toFloat() / newHeight

        forEach {
            val frameNewWidth = (this.width * xFactor).toInt()
            val frameNewHeight = (this.height * yFactor).toInt()
            resize(frameNewWidth, frameNewHeight)
        }
    }
}
