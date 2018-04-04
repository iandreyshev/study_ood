package outputStream

abstract class OutputDecorator2(
        private val stream: IOutputStream
) : IOutputStream {
    override fun write(byte: Byte) = stream.write(transformByte(byte))

    override fun write(data: ByteArray, dataSize: Int) {
        val result = transformSeveralBytes(data, dataSize)
        stream.write(result, result.size)
    }

    protected abstract fun transformByte(byte: Byte): Byte

    protected abstract fun transformSeveralBytes(data: ByteArray, dataSize: Int): ByteArray
}
