package inputStream

abstract class InputDecorator(
        private val stream: IInputStream
) : IInputStream {
    final override val isEof = stream.isEof

    final override fun read() = transformByte(stream.read())

    final override fun read(dstData: MutableCollection<Byte>, dataSize: Int) {
        super.read(dstData, dataSize)
    }

    protected abstract fun transformByte(byte: Byte): Byte
}
