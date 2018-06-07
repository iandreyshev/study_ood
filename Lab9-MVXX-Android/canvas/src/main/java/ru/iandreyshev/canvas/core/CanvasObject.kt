package ru.iandreyshev.canvas.core

import ru.iandreyshev.canvas.style.IConstStyle
import ru.iandreyshev.canvas.style.ObservableStyle
import ru.iandreyshev.canvas.style.Style
import ru.iandreyshev.geometry.frame.Frame
import ru.iandreyshev.geometry.frame.IConstFrame
import ru.iandreyshev.geometry.frame.ObservableFrame

abstract class CanvasObject(
        frame: IConstFrame,
        style: IConstStyle
) {

    val frame: IConstFrame
        get() = mFrame

    val style: IConstStyle
        get() = mStyle

    var updateFrameListener: (Frame, IConstFrame) -> Unit = { _, _ -> }
    var updateStyleListener: (Style, IConstStyle) -> Unit = { _, _ -> }
    var deleteListener: (CanvasObject) -> Unit = { _ -> }

    private val mFrame: Frame = Frame(frame)
    private val mStyle: Style = Style(style)

    fun update(frame: IConstFrame) =
            updateFrameListener(mFrame, frame)

    fun update(style: IConstStyle) =
            updateStyleListener(mStyle, style)

    fun delete() =
            deleteListener(this)

    abstract fun accept(visitor: ICanvasObjectVisitor)

}
