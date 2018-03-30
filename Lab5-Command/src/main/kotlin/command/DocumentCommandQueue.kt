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
        mRedoCommands.removeAll {
            it.destroy()
            true
        }
        command.doIt()
    }

    @Throws(IllegalStateException::class)
    override fun undo() {
        if (mUndoCommands.empty()) {
            throw IllegalStateException("Nothing to undo")
        }

        val command = mUndoCommands.pop()
        command.undo()
        mRedoCommands.push(command)
    }

    @Throws(IllegalStateException::class)
    override fun redo() {
        if (mRedoCommands.empty()) {
            throw IllegalStateException("Nothing to redo")
        }

        mRedoCommands.pop().doIt()
    }

    private fun ICommand.doIt() {
        execute()
        mUndoCommands.push(this)

        if (mUndoCommands.size > memorySize) {
            mUndoCommands.removeAt(memorySize)
        }
    }
}
