package command

class ReplaceTextCommand(
        private val oldText: String,
        private val newText: String,
        private val onReplaceListener: (t: String) -> Unit
) : Command() {
    override fun onExecute() = onReplaceListener(newText)

    override fun onUndo() = onReplaceListener(oldText)
}
