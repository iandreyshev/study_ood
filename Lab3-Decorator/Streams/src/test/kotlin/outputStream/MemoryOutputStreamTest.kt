package outputStream

import org.junit.Assert.*
import org.junit.Test

class MemoryOutputStreamTest {
    @Test
    fun canWriteOneByteToMemory() {
        val memory = MutableList<Byte>(0) { 0 }
        val stream = MemoryOutputStream(memory)
        val byte: Byte = 13

        stream.write(byte)

        assertEquals(byte, memory.first())
    }

    @Test
    fun canWriteCollectionOfBytesToMemory() {
        val bytes = ByteArray(123) { it.toByte() }

        val bytesCountToWrite = bytes.size / 2
        val memory = MutableList<Byte>(0) { 0 }
        val stream = MemoryOutputStream(memory)

        stream.write(bytes, bytesCountToWrite)

        memory.forEachIndexed { index, byte ->
            assertEquals(bytes[index], byte)
        }
    }
}
