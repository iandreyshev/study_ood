package compressor

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import randByte

class SimpleCompressorTest {

    private lateinit var mCompressor: SimpleCompressor

    @Before
    fun setup() {
        mCompressor = SimpleCompressor()
    }

    @Test
    fun decompressBytesWithCompressionReturnSourceBytes() {
        val sourceBytes = ByteArray(1000000, { randByte(it + 1) })

        val compressedBytes = mCompressor.compress(sourceBytes)
        val decompressedBytes = mCompressor.decompress(compressedBytes)

        decompressedBytes.forEachIndexed { index, byte ->
            assertEquals(sourceBytes[index], byte)
        }
    }
}
