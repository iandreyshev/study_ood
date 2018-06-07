package ru.iandreyshev.canvas.command

import ru.iandreyshev.command.Command
import ru.iandreyshev.geometry.frame.Frame
import ru.iandreyshev.geometry.vector.Vec2f

internal class MoveFrameCommand(
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
