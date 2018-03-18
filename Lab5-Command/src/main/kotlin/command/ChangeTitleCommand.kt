package command

class ChangeTitleCommand(
        private var title: String,
        private val newTitle: String
) : ICommand {
    private val mTitleBeforeExecute = title

    override fun execute() {
        title = newTitle
    }

    override fun undo() {
        title = mTitleBeforeExecute
    }
}
