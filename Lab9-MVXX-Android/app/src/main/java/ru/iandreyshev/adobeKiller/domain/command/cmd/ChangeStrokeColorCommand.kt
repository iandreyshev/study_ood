package ru.iandreyshev.adobeKiller.domain.command.cmd

import ru.iandreyshev.adobeKiller.domain.command.Command
import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.Color
import ru.iandreyshev.adobeKiller.presentation.drawing.style.IStyle

class ChangeStrokeColorCommand(
        private val style: IStyle,
        private val color: Color
) : Command() {

    private val mOldColor: Color = style.strokeColor

    override fun onExecute() {
        style.strokeColor = color
    }

    override fun onUndo() {
        style.strokeColor = mOldColor
    }

}
