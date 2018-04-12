package compressor

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import outputStream.IOutputStream
import outputStream.MemoryOutputStream

class RleCompressorTest {
    private lateinit var mOutput: MutableList<Byte>
    private lateinit var mOutputStream: IOutputStream
    private lateinit var mCompressor: RleCompressor

    @Before
    fun setup() {
        mOutput = mutableListOf()
        mOutputStream = MemoryOutputStream(mOutput)
        mCompressor = RleCompressor(mOutputStream)
    }

    @Test
    fun canCompressOneByte() {
        mCompressor.use { compressor ->
            compressor.write(1)
        }

        assertEquals(2, mOutput.size)
        assertEquals(1, mOutput[0])
        assertEquals(1, mOutput[1])
    }

    @Test
    fun canCompressSeveralBytes() {
        mCompressor.use { with(it) {
            repeat(300) {
                write(0)
            }
            repeat(300) {
                write(1)
            }
        } }

        assertEquals(12, mOutput.size)
        print(mOutput.joinToString(separator = ", "))
    }
}