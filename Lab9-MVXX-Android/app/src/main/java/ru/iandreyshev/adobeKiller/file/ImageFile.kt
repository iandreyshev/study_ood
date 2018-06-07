package ru.iandreyshev.adobeKiller.file

import ru.iandreyshev.canvas.file.IFile
import java.io.File

class ImageFile(
        private val file: File
) : IFile {

    private var mBytes: ByteArray? = null

    override fun bytes(): ByteArray {
        mBytes?.let { return it }

        return try {
            mBytes = file.readBytes()
            mBytes!!
        } catch (e: Exception) {
            byteArrayOf()
        }
    }

    override fun flush() {
        mBytes = null
    }

    override fun delete() {
        file.deleteRecursively()
    }

}