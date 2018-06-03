package ru.iandreyshev.adobeKiller.domain.command.cmd

import ru.iandreyshev.adobeKiller.domain.command.Command
import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.Color
import ru.iandreyshev.adobeKiller.presentation.drawing.style.Style

class ChangeFillColorCommand(
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
