package command

class InsertImageCommand(
        private val path: String,
        private val width: Int,
        private val height: Int,
        private val position: Int
) : ICommand {
    override fun execute() {
    }

    override fun undo() {
    }
}
