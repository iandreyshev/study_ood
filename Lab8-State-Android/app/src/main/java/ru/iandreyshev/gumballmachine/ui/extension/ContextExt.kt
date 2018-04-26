package ru.iandreyshev.gumballmachine.ui.extension

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View

fun Context.inflate(@LayoutRes resId: Int): View =
        LayoutInflater.from(this).inflate(resId, null)
