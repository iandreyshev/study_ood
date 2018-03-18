package command

interface ICommandQueue {
    val canUndo: Boolean

    val canRedo: Boolean

    fun apply(command: ICommand)

    fun undo()

    fun redo()
}
