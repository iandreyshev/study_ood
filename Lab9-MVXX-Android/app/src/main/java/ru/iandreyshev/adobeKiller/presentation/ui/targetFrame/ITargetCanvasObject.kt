package ru.iandreyshev.adobeKiller.presentation.ui.targetFrame

import ru.iandreyshev.geometry.frame.Frame
import ru.iandreyshev.canvas.style.Style

interface ITargetCanvasObject {

    val frame: Frame
    val style: Style

    fun applyChanges()
    fun delete()

}
