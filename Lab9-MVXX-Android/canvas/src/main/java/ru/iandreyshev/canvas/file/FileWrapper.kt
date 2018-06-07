package ru.iandreyshev.canvas.file

import java.io.File

class FileWrapper(override val path: String) : IFile {

    constructor(file: File) : this(file.absolutePath)

    override fun bytes(): ByteArray? {
        return try {
            File(path).readBytes()
        } catch (ex: Exception) {
            null
        }
    }

    override fun delete() {
        try {
            File(path).deleteRecursively()
        } catch (ex: Exception) {
            // nothing to do
        }
    }

}
