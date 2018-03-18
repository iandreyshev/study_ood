package inputStream

import java.io.BufferedInputStream
import java.io.File
import java.io.FileInputStream

class FileInputStream(filePath: String) : IInputStream {
    private val mStream: ByteIterator = BufferedInputStream(FileInputStream(File(filePath))).iterator()

    override val isEof: Boolean
        get() = !mStream.hasNext()

    override fun read(): Byte = mStream.nextByte()
}
