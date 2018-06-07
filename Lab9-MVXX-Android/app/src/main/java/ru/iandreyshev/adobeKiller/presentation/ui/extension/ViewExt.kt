package ru.iandreyshev.adobeKiller.presentation.ui.extension

import android.view.View

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.visibleIfOrGone(isVisible: Boolean) {
    if (isVisible) visible() else gone()
}
