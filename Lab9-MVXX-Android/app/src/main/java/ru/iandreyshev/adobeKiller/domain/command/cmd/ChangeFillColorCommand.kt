package ru.iandreyshev.adobeKiller.domain.command.cmd

import ru.iandreyshev.adobeKiller.domain.command.Command
import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.Color
import ru.iandreyshev.adobeKiller.presentation.drawing.style.IStyle

class ChangeFillColorCommand(
        private val style: IStyle,
        private val color: Color
) : Command() {

    private val mOldColor: Color = style.fillColor

    override fun onExecute() {
        style.fillColor = color
    }

    override fun onUndo() {
        style.fillColor = mOldColor
    }

}
