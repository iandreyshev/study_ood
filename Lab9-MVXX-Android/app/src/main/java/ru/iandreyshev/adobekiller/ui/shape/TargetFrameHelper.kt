package ru.iandreyshev.adobekiller.ui.shape

import ru.iandreyshev.adobekiller.model.shape.frame.*
import ru.iandreyshev.adobekiller.model.container.Vec2f

class TargetFrameHelper(
        private val minWidth: Float,
        private val minHeight: Float,
        private val circleTouchRadius: Float) {

    var target: IFrame? = null

    private var mOnFrameChanged: ((IFrame) -> Unit)? = null
    private val mMoveToPosition = Vec2f()

    fun handleMoveEvent(lastX: Float?, lastY: Float?, newX: Float, newY: Float) {
        lastX ?: return
        lastY ?: return

        val currentFrame = target ?: return

        val newFrame = handleCircleMove(currentFrame, lastX, lastY, newX, newY) ?: kotlin.run {
            handleRectMove(currentFrame, lastX, lastY, newX, newY)
        }

        newFrame?.let {
            target = it
            mOnFrameChanged?.invoke(it)
        }
    }

    fun onFrameChanged(action: (frame: IFrame) -> Unit) {
        mOnFrameChanged = action
    }

    private fun handleCircleMove(frame: IFrame, lastX: Float, lastY: Float, newX: Float, newY: Float): IFrame? {
        fun hitTest(circleX: Float, circleY: Float): Boolean {
            val xRange = ((circleX - circleTouchRadius)..(circleX + circleTouchRadius))
            val yRange = ((circleY - circleTouchRadius)..(circleY + circleTouchRadius))

            return xRange.contains(lastX) && yRange.contains(lastY) ||
                    xRange.contains(newX) && yRange.contains(newY)
        }

        return when {
            hitTest(frame.left, frame.top) -> {
                // Left top
                if (newX > frame.right - minWidth) return null
                if (newY > frame.bottom - minHeight) return null

                val newWidth = frame.right - newX
                val newHeight = frame.bottom - newY

                return Frame(Vec2f(newX, newY), newWidth, newHeight)
            }
            hitTest(frame.right, frame.top) -> {
                // Right top
                if (newX < frame.left + minWidth) return null
                if (newY > frame.bottom - minHeight) return null

                val newWidth = newX - frame.left
                val newHeight = frame.bottom - newY

                return Frame(Vec2f(frame.left, newY), newWidth, newHeight)
            }
            hitTest(frame.right, frame.bottom) -> {
                // Right bottom
                if (newX < frame.left + minWidth) return null
                if (newY < frame.top + minHeight) return null

                val newWidth = newX - frame.left
                val newHeight = newY - frame.top

                return Frame(frame.position, newWidth, newHeight)
            }
            hitTest(frame.left, frame.bottom) -> {
                // Left bottom
                if (newX > frame.right - minWidth) return null
                if (newY < frame.top + minHeight) return null

                val newWidth = frame.right - newX
                val newHeight = newY - frame.top

                return Frame(Vec2f(newX, frame.top), newWidth, newHeight)
            }
            else -> null
        }
    }

    private fun handleRectMove(frame: IFrame, lastX: Float, lastY: Float, newX: Float, newY: Float): IFrame? {
        if (!frame.hitTest(lastX, lastY)) {
            return null
        }

        mMoveToPosition.x = frame.position.x + newX - lastX
        mMoveToPosition.y = frame.position.y + newY - lastY

        return Frame(mMoveToPosition, frame.width, frame.height)
    }
}
