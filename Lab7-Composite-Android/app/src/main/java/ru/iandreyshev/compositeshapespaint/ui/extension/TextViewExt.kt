package ru.iandreyshev.compositeshapespaint.ui.extension

import android.widget.TextView

fun TextView.setTextOrGone(text: String?) {
    visibleIfOrGone(text != null)
    text?.let { this.text = it }
}
