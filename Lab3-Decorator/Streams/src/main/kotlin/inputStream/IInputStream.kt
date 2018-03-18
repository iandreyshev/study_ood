package inputStream

interface IInputStream {
    val isEof: Boolean

    fun read(): Byte

    fun read(dstData: MutableCollection<Byte>, dataSize: Int) = repeat(dataSize) {
        dstData.add(read())
    }
}
