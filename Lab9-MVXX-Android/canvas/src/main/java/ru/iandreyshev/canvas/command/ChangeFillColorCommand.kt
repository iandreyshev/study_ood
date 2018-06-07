package ru.iandreyshev.canvas.command

import ru.iandreyshev.canvas.style.Color
import ru.iandreyshev.canvas.style.Style
import ru.iandreyshev.command.Command

internal class ChangeFillColorCommand(
        private val style: Style,
        private val oldColor: Color,
        private val newColor: Color
) : Command() {

    override fun onExecute() {
        style.fillColor = newColor
    }

    override fun onUndo() {
        style.fillColor = oldColor
    }

}
