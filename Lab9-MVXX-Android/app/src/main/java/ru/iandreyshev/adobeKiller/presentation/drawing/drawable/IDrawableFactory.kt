package ru.iandreyshev.adobeKiller.presentation.drawing.drawable

interface IDrawableFactory {

    fun create(shapeName: String): IDrawable?

}
