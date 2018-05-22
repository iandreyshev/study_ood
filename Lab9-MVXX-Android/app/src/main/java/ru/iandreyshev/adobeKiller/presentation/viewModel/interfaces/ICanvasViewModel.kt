package ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces

import ru.iandreyshev.adobeKiller.domain.model.CanvasData
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawable

interface ICanvasViewModel : IViewModel {

    fun setCanvasData(canvasData: CanvasData)

    fun updateShapes(shapes: List<IDrawable>)

    fun setTarget(shape: IDrawable?)

}
