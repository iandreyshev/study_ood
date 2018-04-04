package command

import java.util.*

class DocumentCommandQueue(memorySize: Int) : ICommandQueue {
    private val mUndoCommands = StackWithLimit(memorySize)
    private val mRedoCommands = StackWithLimit(memorySize)

    override val canUndo: Boolean
        get() = !mUndoCommands.empty()
    override val canRedo: Boolean
        get() = !mRedoCommands.empty()

    override fun apply(command: Command) {
        mRedoCommands.forEach { it.destroy() }
        mRedoCommands.clear()

        command.execute()
        mUndoCommands.push(command)
    }

    override fun undo() {
        if (mUndoCommands.empty()) {
            throw IllegalStateException("Nothing to undo")
        }

        val command = mUndoCommands.pop()
        command.undo()
        mRedoCommands.push(command)
    }

    override fun redo() {
        if (mRedoCommands.empty()) {
            throw IllegalStateException("Nothing to redo")
        }

        val command = mRedoCommands.pop()
        command.execute()
        mUndoCommands.push(command)
    }

    class StackWithLimit(private val memorySize: Int) : Stack<Command>() {
        override fun push(command: Command): Command {
            super.push(command)
            if (size > memorySize) {
                removeAt(memorySize).destroy()
            }
            return command
        }
    }
}
