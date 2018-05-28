package ru.iandreyshev.adobeKiller.domain.command

interface ICommandQueue {

    val canUndo: Boolean
    val canRedo: Boolean

    fun apply(command: Command)
    fun apply(buildCommandAction: () -> Command) {
        apply(buildCommandAction())
    }

    fun undo()
    fun redo()
    fun clear()

}
