package inputStream

import org.junit.Assert.*
import org.junit.Test
import pathToTxt

class FileInputStreamTest {
    @Test
    fun canReturnEofIfFileIsEmpty() {
        val stream = FileInputStream("EmptyFile".pathToTxt)
        assertTrue(stream.isEof)
    }

    @Test
    fun canReturnByteFromFile() {
        val stream = FileInputStream("FileWith5Bytes".pathToTxt)
        repeat(5) {
            assertFalse(stream.isEof)

            val byte = stream.read()
            assertEquals(it.toString().first(), byte.toChar())
        }

        assertTrue(stream.isEof)
    }

    @Test
    fun canReadBytesToMutableCollection() {
        val stream = FileInputStream("FileWith5Bytes".pathToTxt)
        val collection = ArrayList<Byte>(5)
        stream.read(collection, 5)

        repeat(5) {
            assertEquals(it.toString().first(), collection[it].toChar())
        }
    }

    @Test(expected = Exception::class)
    fun throwExceptionWhenReadWithEOF() {
        val stream = FileInputStream("EmptyFile".pathToTxt)

        assertTrue(stream.isEof)
        stream.read()
    }
}
