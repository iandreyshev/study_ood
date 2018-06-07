package ru.iandreyshev.adobeKiller.presentation.viewModel

import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawable
import ru.iandreyshev.adobeKiller.presentation.ui.targetFrame.ITargetCanvasObject

interface ICanvasViewModel {

    fun setTarget(target: ITargetCanvasObject?)
    fun insert(canvasObject: IDrawable)
    fun reDraw()
    fun clear()

}
