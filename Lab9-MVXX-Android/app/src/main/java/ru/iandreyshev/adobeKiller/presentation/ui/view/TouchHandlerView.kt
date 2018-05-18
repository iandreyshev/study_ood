package ru.iandreyshev.adobeKiller.presentation.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import ru.iandreyshev.adobeKiller.presentation.ui.OnTouchCallback
import ru.iandreyshev.adobeKiller.presentation.ui.OnTouchMoveCallback

abstract class TouchHandlerView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    open var onTouch: OnTouchCallback? = null
    open var onMove: OnTouchMoveCallback? = null

    final override fun onTouchEvent(event: MotionEvent?): Boolean {
        event ?: return super.onTouchEvent(event)

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                handleClick(event)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                handleMove(event)
                return true
            }
        }

        return super.onTouchEvent(event)
    }

    private fun handleClick(event: MotionEvent) {
        onTouch?.invoke(event.x, event.y)
    }

    private fun handleMove(event: MotionEvent) {
        if (event.historySize < 1) {
            return
        }

        onMove?.let { action ->
            val lastX = event.getHistoricalX(0)
            val lastY = event.getHistoricalY(0)
            action(lastX, lastY, event.x, event.y)
        }
    }
}
