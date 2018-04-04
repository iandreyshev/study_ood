package outputStream

abstract class OutputDecorator(
        private val stream: IOutputStream
) : IOutputStream {
    final override fun write(byte: Byte) = stream.write(transformByte(byte))

    final override fun write(data: ByteArray, dataSize: Int) {
        super.write(data, dataSize)
    }

    protected abstract fun transformByte(byte: Byte): Byte
}
