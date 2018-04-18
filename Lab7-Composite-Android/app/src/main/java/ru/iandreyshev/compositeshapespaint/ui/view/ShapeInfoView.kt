package ru.iandreyshev.compositeshapespaint.ui.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import kotlinx.android.synthetic.main.activity_main.view.*
import shape.IShape

class ShapeInfoView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    fun setShape(shape: IShape) = with(shape) {
        tvPosition.text = positionString
        tvSize.text = sizeString
        tvStrokeSize.text = strokeSizeString
    }

    private val IShape.positionString: String
        get() = "x: ${frame.position.x} y: ${frame.position.y}"
    private val IShape.sizeString: String
        get() = "w: ${frame.width} h: ${frame.height}"
    private val IShape.strokeSizeString: String
        get() = "ss: ${getStrokeSize()}"
}
