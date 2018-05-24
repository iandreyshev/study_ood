package ru.iandreyshev.adobeKiller.domain.command

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

    protected abstract fun onExecute()
    protected abstract fun onUndo()

    protected open fun onDestroyExecuted() = Unit
    protected open fun onDestroyNotExecuted() = Unit

}
