package outputStream

abstract class OutputDecorator(
        private val stream: IOutputStream
) : IOutputStream {
    final override fun write(byte: Byte) {
        val newByte = transformByte(byte) ?: return
        stream.write(newByte)
    }

    final override fun write(data: ByteArray, dataSize: Int) {
        super.write(data, dataSize)
    }

    protected abstract fun transformByte(byte: Byte): Byte?
}
