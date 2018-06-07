package ru.iandreyshev.canvas.file

interface IFile {

    fun bytes(): ByteArray
    fun flush()
    fun delete()

}
