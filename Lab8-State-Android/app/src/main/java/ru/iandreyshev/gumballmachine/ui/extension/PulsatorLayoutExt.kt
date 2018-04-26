package ru.iandreyshev.gumballmachine.ui.extension

import pl.bclogic.pulsator4droid.library.PulsatorLayout

fun PulsatorLayout.onClick() {
    stop()
    start()
}
