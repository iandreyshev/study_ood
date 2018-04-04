package inputStream

abstract class InputDecorator2(
        private val stream: IInputStream
) : IInputStream {
    override val isEof: Boolean
        get() = stream.isEof

    override fun read(): Byte = transformByte(stream.read())

    override fun read(dstData: MutableCollection<Byte>, dataSize: Int) {
        stream.read(dstData, dataSize)
        transformSeveralBytes(dstData)
    }

    protected abstract fun transformByte(byte: Byte): Byte

    protected abstract fun transformSeveralBytes(dstData: MutableCollection<Byte>)
}
