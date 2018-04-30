package ru.iandreyshev.compositeshapespaint.ui.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import kotlinx.android.synthetic.main.view_shape_info.view.*
import ru.iandreyshev.compositeshapespaint.model.canvas.Color
import ru.iandreyshev.compositeshapespaint.model.shape.IShape
import ru.iandreyshev.compositeshapespaint.ui.extension.*

class ShapeInfoView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    fun setShape(shape: IShape?) {
        flFillColor.visibleIfOrGone(shape != null)
        cvFillColor.fill(shape?.getFillColor())

        flStrokeColor.visibleIfOrGone(shape != null)
        cvStrokeColor.fill(shape?.getStrokeColor())

        shape?.getStrokeColor().apply {
            cvStrokeColorTint.visible()
            when (this) {
                null -> cvStrokeColorTint.invisible()
                Color.NONE -> cvStrokeColorTint.invisible()
                else -> {} // skip
            }
        }

        cvFillColor.invalidate()
        cvStrokeColor.invalidate()
    }

    fun setOnFillColorClick(action: () -> Unit) =
            cvFillColor.setOnClickListener { action() }

    fun setOnStrokeColorClick(action: () -> Unit) =
            cvStrokeColor.setOnClickListener { action() }
}
