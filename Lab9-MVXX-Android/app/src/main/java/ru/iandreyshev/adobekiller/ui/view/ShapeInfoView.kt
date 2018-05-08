package ru.iandreyshev.adobekiller.ui.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import kotlinx.android.synthetic.main.view_shape_info.view.*
import ru.iandreyshev.adobekiller.ui.extension.fill
import ru.iandreyshev.adobekiller.model.canvas.Color
import ru.iandreyshev.adobekiller.model.shape.IShape
import ru.iandreyshev.adobekiller.ui.extension.invisible
import ru.iandreyshev.adobekiller.ui.extension.visible
import ru.iandreyshev.adobekiller.ui.extension.visibleIfOrGone

class ShapeInfoView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    fun setShape(shape: IShape?) {
        flFillColor.visibleIfOrGone(shape != null)
        cvFillColor.fill(shape?.style?.getFillColor())

        flStrokeColor.visibleIfOrGone(shape != null)
        cvStrokeColor.fill(shape?.style?.getStrokeColor())

        shape?.style?.getStrokeColor().apply {
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
            cvFillColorButton.setOnClickListener { action() }

    fun setOnStrokeColorClick(action: () -> Unit) =
            cvStrokeColorButton.setOnClickListener { action() }
}
