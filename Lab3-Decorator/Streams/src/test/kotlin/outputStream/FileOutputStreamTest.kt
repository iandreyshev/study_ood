package outputStream

import org.junit.Assert.*
import org.junit.Test
import pathToTxt
import java.io.File

class FileOutputStreamTest {
    @Test
    fun canWriteByteToFile() {
        val path = "temp".pathToTxt
        val stream = FileOutputStream(path)
        val randBytes = ByteArray(13900) { it.toByte() }

        randBytes.forEach {
            stream.write(it)
        }

        val bytesFromFile = File(path).inputStream().readBytes()

        assertEquals(randBytes.size, bytesFromFile.size)
        bytesFromFile.forEachIndexed { index, byte ->
            assertEquals(randBytes[index], byte)
        }
    }

    @Test
    fun canWriteBlockOfBytesToFile() {
        val path = "temp".pathToTxt
        val stream = FileOutputStream(path)
        val randBytes = ByteArray(13900) { it.toByte() }

        stream.write(randBytes, randBytes.size)

        val bytesFromFile = File(path).inputStream().readBytes()

        assertEquals(randBytes.size, bytesFromFile.size)
        bytesFromFile.forEachIndexed { index, byte ->
            assertEquals(randBytes[index], byte)
        }
    }
}