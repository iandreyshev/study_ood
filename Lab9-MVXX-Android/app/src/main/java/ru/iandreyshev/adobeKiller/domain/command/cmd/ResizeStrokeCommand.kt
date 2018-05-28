package ru.iandreyshev.adobeKiller.domain.command.cmd

import ru.iandreyshev.adobeKiller.domain.command.Command
import ru.iandreyshev.adobeKiller.presentation.drawing.style.IStyle

class ResizeStrokeCommand(
        private val style: IStyle,
        private val oldSize: Float,
        private val newSize: Float
) : Command() {

    override fun onExecute() {
        style.strokeSize = newSize
    }

    override fun onUndo() {
        style.strokeSize = oldSize
    }

}
