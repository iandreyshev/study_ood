package io

abstract class DocumentActionParser {
    companion object {
        private val SEPARATOR = Regex(" +")
    }

    private lateinit var mParams: List<String>

    @Throws(Exception::class)
    fun apply(command: String): Boolean {
        val cmd = command.trim().split(SEPARATOR, limit = 2)[0]
        val action = getCommand(cmd) ?: throw IllegalArgumentException("Command '$cmd' not found")
        val args = command.split(SEPARATOR, limit = action.paramsCount + 1)
        mParams = args.subList(1, args.count())

        action.action.invoke()

        return !isExit()
    }

    protected abstract fun getCommand(command: String): Command?

    protected abstract fun isExit(): Boolean

    protected fun intAt(position: Int): Int {
        try {
            return mParams[position].toInt()
        } catch (ex: Exception) {
            throw IllegalArgumentException("Expected integer at position $position")
        }
    }

    protected fun stringAt(position: Int): String {
        try {
            return mParams[position]
        } catch (ex: Exception) {
            throw IllegalArgumentException("Expected string at position $position")
        }
    }

    data class Command(
            val action: () -> Unit,
            val paramsCount: Int = 0
    )
}
