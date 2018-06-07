package ru.iandreyshev.localstorage

import ru.iandreyshev.canvas.file.IFile

internal class StorageFileWrap(
        private val mBytes: ByteArray
) : IFile {

    override fun bytes(): ByteArray = mBytes

    override fun flush() = Unit

    override fun delete() = Unit

}
