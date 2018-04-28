package ru.iandreyshev.compositeshapespaint.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import ru.iandreyshev.compositeshapespaint.ui.OnTouchMoveCallback

abstract class TouchHandlerView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var onMove: OnTouchMoveCallback? = null

    final override fun onTouchEvent(event: MotionEvent?): Boolean {
        event ?: return false

        when (event.action) {
            MotionEvent.ACTION_MOVE -> ::handleMove
            else -> return true
        }(event)

        return true
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
