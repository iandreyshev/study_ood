package ru.iandreyshev.adobekiller.model.shape.frame

import ru.iandreyshev.adobekiller.model.container.Vec2f
import ru.iandreyshev.adobekiller.model.extension.ISimpleIterator
import ru.iandreyshev.adobekiller.model.extension.doActionOrFalse

class CompositeFrame(
        private val innerFrames: ISimpleIterator<IFrame>
) : IFrame {

    override val width: Float
        get() = getSize(position.x, { it.position.x }, { it.width })

    override val height: Float
        get() = getSize(position.y, { it.position.y }, { it.height })

    override var position: Vec2f
        get() {
            var x = Float.MAX_VALUE
            var y = Float.MAX_VALUE
            val isNotEmpty = innerFrames.doActionOrFalse { frame ->
                if (frame.position.x < x) {
                    x = frame.position.x
                }
                if (frame.position.y < y) {
                    y = frame.position.y
                }
            }

            return if (isNotEmpty) Vec2f(x, y) else Vec2f()
        }
        set(value) {
            val currPosition = position
            val offsetX = value.x - currPosition.x
            val offsetY = value.y - currPosition.y

            innerFrames.forEach { frame ->
                frame.position = Vec2f(frame.position.x + offsetX, frame.position.y + offsetY)
            }
        }

    override fun resize(newWidth: Float, newHeight: Float) {
        val positionBefore = this@CompositeFrame.position
        val widthBefore = this@CompositeFrame.width
        val heightBefore = this@CompositeFrame.height

        val resizeWidthProportion = newWidth / widthBefore
        val resizeHeightProportion = newHeight / heightBefore

        innerFrames.forEach { frame ->
            val xPositionProportion = (frame.position.x - positionBefore.x) / widthBefore
            val yPositionProportion = (frame.position.y - positionBefore.y) / heightBefore

            frame.resize(
                    newWidth = frame.width * resizeWidthProportion,
                    newHeight = frame.height * resizeHeightProportion)

            frame.position = Vec2f(
                    x = positionBefore.x + newWidth * xPositionProportion,
                    y = positionBefore.y + newHeight * yPositionProportion)
        }
    }

    private fun getSize(
            positionOnAxis: Float,
            getPositionOnAxis: (IFrame) -> Float,
            getSizeOnAxis: (IFrame) -> Float): Float {
        var max = Float.MIN_VALUE

        val isNotEmpty = innerFrames.doActionOrFalse { frame ->
            val newMax = getPositionOnAxis(frame) + getSizeOnAxis(frame)
            if (newMax > max) {
                max = newMax
            }
        }

        return if (isNotEmpty) max - positionOnAxis else 0f
    }
}
