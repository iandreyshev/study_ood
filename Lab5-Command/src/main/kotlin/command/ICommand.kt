package command

interface ICommand {
    fun execute()

    fun undo()

    fun destroy() {
        // Nothing to do
    }
}
