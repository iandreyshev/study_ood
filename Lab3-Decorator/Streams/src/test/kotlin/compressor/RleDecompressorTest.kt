package compressor

import inputStream.MemoryInputStream
import junit.framework.TestCase.assertEquals
import org.junit.Test
import outputStream.MemoryOutputStream

class RleDecompressorTest {
    @Test
    fun canDecompressBytes() {
        val memory: MutableList<Byte> = mutableListOf()
        val stream = MemoryOutputStream(memory)
        val compressor = RleCompressor(stream)

        compressor.use { with(it) {
            kotlin.repeat(319) {
                write(0)
            }
            kotlin.repeat(543) {
                write(1)
            }
        } }

        val decompressor = createDecompressor(memory)

        repeat(319) {
            assertEquals(0, decompressor.read())
        }

        repeat(543) {
            assertEquals(1, decompressor.read())
        }
    }

    private fun createDecompressor(memory: MutableList<Byte>): RleDecompressor {
        val inputStream = MemoryInputStream(memory)
        return RleDecompressor(inputStream)
    }
}
