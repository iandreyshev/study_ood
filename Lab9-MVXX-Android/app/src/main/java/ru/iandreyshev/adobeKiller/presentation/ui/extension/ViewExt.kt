package ru.iandreyshev.adobeKiller.presentation.ui.extension

import android.support.annotation.DrawableRes
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

fun View.visibleIfOrInvisible(isVisible: Boolean) {
    if (isVisible) visibility else invisible()
}

fun View.setBackgroundResourceOrGone(@DrawableRes resId: Int?) {
    visibleIfOrGone(resId != null)
    resId?.let { setBackgroundResource(it) }
}
