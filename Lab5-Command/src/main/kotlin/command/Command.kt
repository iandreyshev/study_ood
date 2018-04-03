package command

abstract class Command {
    private var mIsExecuted = false

    fun execute() {
        if (mIsExecuted) {
            return
        }

        onExecute()
        mIsExecuted = true
    }

    fun undo() {
        onUndo()
        mIsExecuted = false
    }

    fun destroy() {
        if (mIsExecuted) {
            onDestroyAfterExecute()
        } else {
            onDestroy()
        }
    }

    abstract fun onExecute()

    abstract fun onUndo()

    open fun onDestroy() {
        // Hook
    }

    open fun onDestroyAfterExecute() {
        // Hook
    }
}
