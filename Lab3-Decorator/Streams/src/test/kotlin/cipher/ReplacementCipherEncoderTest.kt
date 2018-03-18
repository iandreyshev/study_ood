package cipher

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import outputStream.IOutputStream

class ReplacementCipherEncoderTest {

    private lateinit var mMockedStream: IOutputStream
    private lateinit var mMockedPermutation: ICodec<Byte>
    private lateinit var mEncoder: ReplacementCipherEncoder

    @Before
    fun setup() {
        mMockedStream = mock()
        mMockedPermutation = mock()
        mEncoder = ReplacementCipherEncoder(mMockedPermutation, mMockedStream)
    }

    @Test
    fun encodeAndWriteOneByteToStream() {
        val byteToWrite = Byte.MIN_VALUE
        val encodedByte = Byte.MAX_VALUE

        whenever(mMockedPermutation.encode(byteToWrite)).thenReturn(encodedByte)

        mEncoder.write(byteToWrite)

        verify(mMockedPermutation).encode(byteToWrite)
        verify(mMockedStream).write(encodedByte)
    }

    @Test
    fun encodeAndWriteCollectionOfBytesToStream() {
        val byteToWrite = Byte.MIN_VALUE
        val encodedByte = Byte.MAX_VALUE
        val bytesToWrite = ByteArray(10000) { byteToWrite }

        whenever(mMockedPermutation.encode(byteToWrite)).thenReturn(encodedByte)

        mEncoder.write(bytesToWrite, bytesToWrite.size)

        verify(mMockedPermutation, times(bytesToWrite.size)).encode(byteToWrite)
        verify(mMockedStream, times(bytesToWrite.size)).write(encodedByte)
    }
}
