package command

import java.util.*

class DocumentCommandQueue(
        private val memorySize: Int
) : ICommandQueue {
    private val mUndoCommands = Stack<ICommand>()
    private val mRedoCommands = Stack<ICommand>()

    override val canUndo: Boolean
        get() = !mUndoCommands.empty()
    override val canRedo: Boolean
        get() = !mRedoCommands.empty()

    override fun apply(command: ICommand) {
        while (!mRedoCommands.empty()) {
            mRedoCommands.first().destroy()
            mRedoCommands.removeAt(0)
        }

        command.doIt()
    }

    @Throws(IllegalStateException::class)
    override fun undo() {
        if (mUndoCommands.empty()) {
            throw IllegalStateException()
        }

        val command = mUndoCommands.pop()
        command.undo()
        mRedoCommands.push(command)
    }

    @Throws(IllegalStateException::class)
    override fun redo() {
        if (mRedoCommands.empty()) {
            throw IllegalStateException()
        }

        mRedoCommands.pop().doIt()
    }

    private fun ICommand.doIt() {
        execute()
        mUndoCommands.push(this)

        if (mUndoCommands.size > memorySize) {
            val removedCommand = mUndoCommands.removeAt(memorySize)
            removedCommand.destroy()
        }
    }
}
