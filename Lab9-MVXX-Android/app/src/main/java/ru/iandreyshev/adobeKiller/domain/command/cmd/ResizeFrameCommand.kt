package ru.iandreyshev.adobeKiller.domain.command.cmd

import ru.iandreyshev.adobeKiller.domain.command.Command
import ru.iandreyshev.adobeKiller.presentation.drawing.container.Vec2f
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame

class ResizeFrameCommand(
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
