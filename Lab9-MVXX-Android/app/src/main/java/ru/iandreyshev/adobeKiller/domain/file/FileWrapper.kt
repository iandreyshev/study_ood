package ru.iandreyshev.adobeKiller.domain.file

import java.io.File

class FileWrapper(
        override val path: String,
        private val threadSleepInMillis: Long = 0
) : IFile {

    constructor(file: File, threadSleepInMillis: Long = 0) : this(file.absolutePath, threadSleepInMillis)

    override fun bytes(): ByteArray? {
        return try {
            Thread.sleep(threadSleepInMillis)
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
