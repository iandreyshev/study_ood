package inputStream

import org.junit.Assert.*
import org.junit.Test

class MemoryInputStreamTest {
    @Test
    fun canReturnEofIfMemoryEmpty() {
        val memory = MutableList<Byte>(0) { 0 }
        val stream = MemoryInputStream(memory)

        assertTrue(stream.isEof)
    }

    @Test
    fun canReturnBytesFromCollection() {
        val memory = MutableList(1000) { index -> index.toByte() }
        val stream = MemoryInputStream(memory)

        memory.forEach { element ->
            assertEquals(element, stream.read())
        }

        assertTrue(stream.isEof)
    }

    @Test
    fun canReadBytesToCollection() {
        val memory = MutableList(1000) { index -> index.toByte() }
        val collection = ArrayList<Byte>(memory.size)
        val stream = MemoryInputStream(memory)

        stream.read(collection, collection.size)

        collection.forEachIndexed() { index, element ->
            assertEquals(memory[index], element)
        }
    }
}