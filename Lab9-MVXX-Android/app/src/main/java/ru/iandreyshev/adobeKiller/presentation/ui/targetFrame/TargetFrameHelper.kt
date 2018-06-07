package ru.iandreyshev.adobeKiller.presentation.ui.targetFrame

import ru.iandreyshev.geometry.vector.Vec2f
import ru.iandreyshev.adobeKiller.presentation.drawing.extension.*
import ru.iandreyshev.geometry.frame.Frame
import ru.iandreyshev.geometry.frame.IConstFrame

class TargetFrameHelper(
        private val minWidth: Float,
        private val minHeight: Float,
        private val circleTouchRadius: Float) {

    var target: IConstFrame? = null

    private var mOnFrameChanged: ((Frame) -> Unit)? = null
    private val mMoveToPosition = Vec2f()

    fun handleMoveEvent(lastX: Float?, lastY: Float?, newX: Float, newY: Float): Boolean {
        lastX ?: return false
        lastY ?: return false

        val currentFrame = target ?: return false
        val newFrame = handleCircleMove(currentFrame, lastX, lastY, newX, newY) ?: kotlin.run {
            handleRectMove(currentFrame, lastX, lastY, newX, newY)
        }

        newFrame?.apply {
            target = this
            mOnFrameChanged?.invoke(this)
        }

        return true
    }

    fun onFrameChanged(action: (frame: Frame) -> Unit) {
        mOnFrameChanged = action
    }

    private fun handleCircleMove(frame: IConstFrame, lastX: Float, lastY: Float, newX: Float, newY: Float): Frame? {
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

                return Frame(Vec2f(frame.x, frame.y), newWidth, newHeight)
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

    private fun handleRectMove(frame: IConstFrame, lastX: Float, lastY: Float, newX: Float, newY: Float): Frame? {
        if (!frame.hitTest(lastX, lastY)) {
            return null
        }

        mMoveToPosition.x = frame.x + newX - lastX
        mMoveToPosition.y = frame.y + newY - lastY

        return Frame(mMoveToPosition, frame.width, frame.height)
    }
}
