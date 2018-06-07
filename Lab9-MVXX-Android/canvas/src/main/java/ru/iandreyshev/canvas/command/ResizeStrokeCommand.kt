package ru.iandreyshev.canvas.command

import ru.iandreyshev.command.Command
import ru.iandreyshev.canvas.style.Style

internal class ResizeStrokeCommand(
        private val style: Style,
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
