package ru.iandreyshev.adobeKiller.domain.command.cmd

import ru.iandreyshev.adobeKiller.domain.command.Command
import ru.iandreyshev.adobeKiller.presentation.drawing.container.Vec2f
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame

class MoveFrameCommand(
        private val frame: Frame,
        oldPosition: Vec2f,
        newPosition: Vec2f
) : Command() {

    private val mNewPosition = Vec2f(newPosition)
    private val mOldPosition = Vec2f(oldPosition)

    override fun onExecute() {
        frame.position = mNewPosition
    }

    override fun onUndo() {
        frame.position = mOldPosition
    }

}
