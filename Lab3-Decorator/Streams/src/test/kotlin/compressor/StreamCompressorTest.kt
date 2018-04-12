package compressor

import junit.framework.TestCase.fail
import org.junit.Before
import org.junit.Test
import outputStream.MemoryOutputStream
import java.util.*

class StreamCompressorTest {
    private lateinit var output: MutableList<Byte>
    private lateinit var memoryStream: MemoryOutputStream
    private lateinit var compressor: RleCompressor

    @Before
    fun setup() {
        output = mutableListOf()
        memoryStream = MemoryOutputStream(output)
        compressor = RleCompressor(memoryStream)
    }

    @Test
    fun canCompressOneByte() {
        for (byte in Byte.MIN_VALUE .. Byte.MAX_VALUE) {
            try {
                compressor.write(byte.toByte())
            } catch (ex: Exception) {
                fail()
            }
        }
    }

    @Test
    fun canCompressSeveralBytes() {
        val randomBytes = ByteArray(10000) {
            Random().nextInt().toByte()
        }

        try {
            compressor.write(randomBytes, randomBytes.size)
        } catch (ex: Exception) {
            println(ex.message)
            fail()
        }
    }
}
