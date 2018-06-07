package ru.iandreyshev.adobeKiller.presentation.ui.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import kotlinx.android.synthetic.main.view_shape_info.view.*
import ru.iandreyshev.adobeKiller.presentation.ui.extension.*
import ru.iandreyshev.canvas.style.Color
import ru.iandreyshev.canvas.style.Style

class ShapeInfoView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var style: Style? = null
        set(value) {
            field = value

            if (field == null) {
                flFillColor.gone()
                flStrokeColor.gone()
                return
            }

            flFillColor.visibleIfOrGone(field != null)
            cvFillColor.fill(field?.fillColor)

            flStrokeColor.visibleIfOrGone(field != null)
            cvStrokeColor.fill(field?.strokeColor)

            field?.strokeColor.apply {
                cvStrokeColorTint.visible()
                when (this) {
                    null -> cvStrokeColorTint.invisible()
                    Color.NONE -> cvStrokeColorTint.invisible()
                    else -> {
                    } // skip
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
