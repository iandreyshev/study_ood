package ru.iandreyshev.adobeKiller.domain.file

interface IFile {

    val path: String

    fun bytes(): ByteArray?
    fun delete()

}
