package ru.iandreyshev.adobekiller.ui.extension

import android.widget.TextView

fun TextView.setTextOrGone(text: String?) {
    visibleIfOrGone(text != null)
    text?.let { this.text = it }
}
