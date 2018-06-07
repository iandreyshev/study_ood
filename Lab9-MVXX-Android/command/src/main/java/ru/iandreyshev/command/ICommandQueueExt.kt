package ru.iandreyshev.command

infix fun ICommandQueue.apply(command: Command) {
    apply(command)
}
