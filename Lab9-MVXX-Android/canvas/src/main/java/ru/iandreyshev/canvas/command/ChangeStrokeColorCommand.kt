package ru.iandreyshev.canvas.command

import ru.iandreyshev.command.Command
import ru.iandreyshev.canvas.style.Color
import ru.iandreyshev.canvas.style.Style

internal class ChangeStrokeColorCommand(
        private val style: Style,
        private val oldColor: Color,
        private val newColor: Color
) : Command() {

    override fun onExecute() {
        style.strokeColor = newColor
    }

    override fun onUndo() {
        style.strokeColor = oldColor
    }

}
