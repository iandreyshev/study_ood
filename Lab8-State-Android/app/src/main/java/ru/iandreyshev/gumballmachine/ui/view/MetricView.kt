package ru.iandreyshev.gumballmachine.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.view_metric.view.*
import ru.iandreyshev.gumballmachine.R

class MetricView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    companion object {
        private const val DEFAULT_TITLE_SIZE = 8f
        private const val DEFAULT_METRIC_SIZE = 20f
    }

    init {
        LayoutInflater.from(context)
                .inflate(R.layout.view_metric, this)

        context.theme.obtainStyledAttributes(attrs, R.styleable.MetricView, 0, 0).apply {
            title.text = getString(R.styleable.MetricView_title)
            title.textSize = getDimension(R.styleable.MetricView_title_size, DEFAULT_TITLE_SIZE)

            metric.text = getString(R.styleable.MetricView_metric)
            metric.textSize = getDimension(R.styleable.MetricView_metric_size, DEFAULT_METRIC_SIZE)
        }
    }
}
