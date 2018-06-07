package ru.iandreyshev.canvas.file

interface IFile {

    val path: String

    fun bytes(): ByteArray?
    fun delete()

}
