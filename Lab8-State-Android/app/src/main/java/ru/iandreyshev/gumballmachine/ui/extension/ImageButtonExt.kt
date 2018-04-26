package ru.iandreyshev.gumballmachine.ui.extension

import android.widget.ImageButton
import pl.bclogic.pulsator4droid.library.PulsatorLayout

fun ImageButton.onClick(pulseLayout: PulsatorLayout, action: () -> Unit) {
    setOnClickListener {
        pulseLayout.onClick()
        action.invoke()
    }
}
