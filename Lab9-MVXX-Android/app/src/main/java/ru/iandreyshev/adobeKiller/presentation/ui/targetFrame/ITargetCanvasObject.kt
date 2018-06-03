package ru.iandreyshev.adobeKiller.presentation.ui.targetFrame

import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.Style

interface ITargetCanvasObject {

    val frame: Frame
    val style: Style

    fun applyChanges()
    fun delete()

}
