package ru.iandreyshev.compositeshapespaint.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import canvas.Color
import kotlinx.android.synthetic.main.activity_main.view.*
import ru.iandreyshev.compositeshapespaint.R
import ru.iandreyshev.compositeshapespaint.converter.ColorConverter
import ru.iandreyshev.compositeshapespaint.model.shape.IShape
import ru.iandreyshev.compositeshapespaint.ui.extension.setTextOrGone
import ru.iandreyshev.compositeshapespaint.ui.extension.visible

class ShapeInfoView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    fun setShape(shape: IShape?) {
        tvName.setTextOrGone(shape?.name)
        tvPosition.setTextOrGone(shape?.positionString)
        tvSize.setTextOrGone(shape?.sizeString)
        tvStrokeSize.setTextOrGone(shape?.strokeSizeString)

        cvFillColor.onDrawAction { view, canvas ->
            val fillColor = shape?.getFillColor()
            when (fillColor) {
                null -> view.setBackgroundResource(R.drawable.undefined_color)
                Color.NONE -> view.setBackgroundResource(R.drawable.none_color)
                else -> canvas.fill(fillColor)
            }
        }

        cvStrokeColor.onDrawAction { view, canvas ->
            val strokeColor = shape?.getStrokeColor()
            when (strokeColor) {
                null -> view.setBackgroundResource(R.drawable.undefined_color)
                Color.NONE -> view.setBackgroundResource(R.drawable.none_color)
                else -> canvas.fill(strokeColor)
            }
        }

        cvFillColor.invalidate()
        cvStrokeColor.invalidate()

        if (shape == null) {
            tvName.visible()
            tvName.text = resources.getString(R.string.shape_info_view_not_selected)
        }
    }

    private val IShape.positionString: String
        get() = "x: ${frame.position.x} y: ${frame.position.y}"
    private val IShape.sizeString: String
        get() = "w: ${frame.width} h: ${frame.height}"
    private val IShape.strokeSizeString: String
        get() = "ss: ${getStrokeSize() ?: 0}"

    private fun Canvas.fill(color: Color) {
        val paint = Paint()
        paint.color = ColorConverter.convert(color)
        paint.style = Paint.Style.FILL
        drawPaint(paint)
    }
}
