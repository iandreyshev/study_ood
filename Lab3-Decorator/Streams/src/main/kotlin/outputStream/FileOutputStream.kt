package outputStream

class FileOutputStream(filePath: String) : IOutputStream {
    private val mStream = java.io.FileOutputStream(filePath)

    override fun write(byte: Byte) {
        mStream.write(byte.toInt())
    }

    override fun close() {
        // Nothing to do
    }
}
