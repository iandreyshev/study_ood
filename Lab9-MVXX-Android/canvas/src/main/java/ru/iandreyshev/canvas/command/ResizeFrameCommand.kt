package ru.iandreyshev.canvas.command

import ru.iandreyshev.command.Command
import ru.iandreyshev.geometry.frame.Frame
import ru.iandreyshev.geometry.vector.Vec2f

internal class ResizeFrameCommand(
        private val frame: Frame,
        oldSize: Vec2f,
        newSize: Vec2f
) : Command() {

    private val mOldSize = Vec2f(oldSize)
    private val mNewSize = Vec2f(newSize)

    override fun onExecute() {
        frame.resize(mNewSize.x, mNewSize.y)
    }

    override fun onUndo() {
        frame.resize(mOldSize.x, mOldSize.y)
    }

}
