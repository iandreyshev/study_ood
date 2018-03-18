package command

import java.util.*

class DocumentCommandQueue(
        private val memorySize: Int
) : ICommandQueue {
    private val mUndoCommands = Stack<ICommand<*>>()
    private val mRedoCommands = Stack<ICommand<*>>()

    override val canUndo: Boolean
        get() = !mUndoCommands.empty()
    override val canRedo: Boolean
        get() = !mRedoCommands.empty()

    override fun <TResult> apply(command: ICommand<TResult>): TResult {
        mRedoCommands.clear()
        return executeCommand(command)
    }

    @Throws(IllegalStateException::class)
    override fun undo() {
        if (mUndoCommands.empty()) {
            throw IllegalStateException()
        }

        val undoCommand = mUndoCommands.pop()
        undoCommand.undo()
        mRedoCommands.push(undoCommand)
    }

    @Throws(IllegalStateException::class)
    override fun redo() {
        if (mRedoCommands.empty()) {
            throw IllegalStateException()
        }

        executeCommand(mRedoCommands.pop())
    }

    private fun <TResult> executeCommand(command: ICommand<TResult>): TResult {
        val result = command.execute()
        mUndoCommands.push(command)

        if (mUndoCommands.size > memorySize) {
            val removedCommand = mUndoCommands.removeAt(memorySize)
            removedCommand.destroy()
        }

        return result
    }
}
