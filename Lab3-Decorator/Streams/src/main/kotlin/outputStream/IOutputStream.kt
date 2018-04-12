package outputStream

import java.io.Closeable

interface IOutputStream : Closeable {
    fun write(byte: Byte)

    fun write(data: ByteArray, dataSize: Int) {
        repeat(dataSize) { index ->
            write(data[index])
        }
    }
}
