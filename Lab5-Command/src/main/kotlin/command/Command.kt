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
            onDestroyExecuted()
        } else {
            onDestroyNotExecuted()
        }
    }

    abstract fun onExecute()

    abstract fun onUndo()

    protected open fun onDestroyExecuted() {
        // Hook
    }

    protected open fun onDestroyNotExecuted() {
        // Hook
    }
}
