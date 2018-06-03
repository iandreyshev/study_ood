package ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces

import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawable
import ru.iandreyshev.adobeKiller.presentation.ui.targetFrame.ITargetCanvasObject

interface ICanvasViewModel : IViewModel {

    fun setTarget(target: ITargetCanvasObject?)
    fun insert(canvasObject: IDrawable)
    fun reDraw()
    fun clear()

}
