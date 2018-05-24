package ru.iandreyshev.adobeKiller.presentation.ui.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import kotlinx.android.synthetic.main.view_shape_info.view.*
import ru.iandreyshev.adobeKiller.presentation.ui.extension.fill
import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.Color
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawable
import ru.iandreyshev.adobeKiller.presentation.ui.extension.invisible
import ru.iandreyshev.adobeKiller.presentation.ui.extension.visible
import ru.iandreyshev.adobeKiller.presentation.ui.extension.visibleIfOrGone

class ShapeInfoView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    fun setShape(shape: IDrawable?) {
        flFillColor.visibleIfOrGone(shape != null)
        cvFillColor.fill(shape?.style?.fillColor)

        flStrokeColor.visibleIfOrGone(shape != null)
        cvStrokeColor.fill(shape?.style?.strokeColor)

        shape?.style?.strokeColor.apply {
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
