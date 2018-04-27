package ru.iandreyshev.compositeshapespaint.ui.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import kotlinx.android.synthetic.main.view_shape_info.view.*
import ru.iandreyshev.compositeshapespaint.R
import ru.iandreyshev.compositeshapespaint.model.canvas.Color
import ru.iandreyshev.compositeshapespaint.model.shape.IShape
import ru.iandreyshev.compositeshapespaint.ui.extension.*

class ShapeInfoView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    fun setShape(shape: IShape?) {
        tvTitle.gone()
        tvPosition.setTextOrGone(shape?.positionString)
        tvSize.setTextOrGone(shape?.sizeString)
        tvStrokeSize.setTextOrGone(shape?.strokeSizeString)

        cvFillColor.fill(shape?.getFillColor())
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

        if (shape == null) {
            tvTitle.visible()
            tvTitle.text = resources.getString(R.string.shape_info_view_not_selected)
        }
    }

    fun setOnFillColorClick(action: () -> Unit) =
            cvFillColor.setOnClickListener { action() }

    fun setOnStrokeColorClick(action: () -> Unit) =
            cvStrokeColor.setOnClickListener { action() }

    private val IShape.positionString: String
        get() = "x: ${frame.position.x.str()} y: ${frame.position.y.str()}"
    private val IShape.sizeString: String
        get() = "w: ${frame.width.str()} h: ${frame.height.str()}"
    private val IShape.strokeSizeString: String
        get() = "ss: ${getStrokeSize()?.str() ?: "?"}"

    private fun Float.str(): String =
            String.format("%.0f", this)
}
