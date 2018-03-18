package command

class InsertParagraphCommand(
        private val text: String,
        private val position: Int
) : ICommand {
    override fun execute() {
    }

    override fun undo() {
    }
}
