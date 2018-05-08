package ru.iandreyshev.adobekiller.ui.extension

import android.app.Activity
import android.util.DisplayMetrics

fun Activity.getWindowSize(): DisplayMetrics {
    val metrics = DisplayMetrics()
    windowManager?.defaultDisplay?.getMetrics(metrics)
    return metrics
}
