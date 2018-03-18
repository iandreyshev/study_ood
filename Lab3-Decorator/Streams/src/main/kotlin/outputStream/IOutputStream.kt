package outputStream

interface IOutputStream {
    fun write(byte: Byte)

    fun write(data: ByteArray, dataSize: Int) = repeat(dataSize) { index ->
        write(data[index])
    }
}
