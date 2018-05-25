package ru.iandreyshev.adobeKiller.domain.command.cmd

import ru.iandreyshev.adobeKiller.domain.command.Command
import ru.iandreyshev.adobeKiller.presentation.drawing.style.IStyle

class ResizeStrokeCommand(
        private val style: IStyle,
        private val size: Float
) : Command() {

    private val mOldSize: Float = style.strokeSize

    override fun onExecute() {
        style.strokeSize = size
    }

    override fun onUndo() {
        style.strokeSize = mOldSize
    }

}
