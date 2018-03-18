package inputStream

abstract class InputDecorator(private val mStream: IInputStream) : IInputStream {
    final override val isEof = mStream.isEof

    final override fun read() = transformByte(mStream.read())

    final override fun read(dstData: MutableCollection<Byte>, dataSize: Int) {
        super.read(dstData, dataSize)
    }

    protected abstract fun transformByte(byte: Byte): Byte
}
