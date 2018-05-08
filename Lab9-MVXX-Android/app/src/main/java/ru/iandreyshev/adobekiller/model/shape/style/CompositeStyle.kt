package ru.iandreyshev.adobekiller.model.shape.style

import ru.iandreyshev.adobekiller.model.canvas.Color
import ru.iandreyshev.adobekiller.model.extension.ISimpleIterator
import ru.iandreyshev.adobekiller.model.extension.forEach2
import ru.iandreyshev.adobekiller.model.extension.getAllSameOrNull

class CompositeStyle(
        private val innerStyles: ISimpleIterator<IStyle>
) : IStyle {

    override fun setFillColor(color: Color) =
            innerStyles.forEach2 { setFillColor(color) }

    override fun getFillColor(): Color? =
            innerStyles.getAllSameOrNull { getFillColor() }

    override fun setStrokeColor(color: Color) =
            innerStyles.forEach2 { setStrokeColor(color) }

    override fun getStrokeColor(): Color? =
            innerStyles.getAllSameOrNull { getStrokeColor() }

    override fun setStrokeSize(size: Float) =
            innerStyles.forEach2 { setStrokeSize(size) }

    override fun getStrokeSize(): Float? =
            innerStyles.getAllSameOrNull { getStrokeSize() }
}
