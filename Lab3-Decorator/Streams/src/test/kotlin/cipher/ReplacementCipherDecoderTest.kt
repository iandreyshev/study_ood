package cipher

import com.nhaarman.mockito_kotlin.*
import inputStream.IInputStream
import org.junit.Assert.*
import org.junit.Test

class ReplacementCipherDecoderTest {
    @Test
    fun returnEofIfStreamReturnEof() {
        fun testEof(isEof: Boolean) {
            val stream: IInputStream = mock()
            whenever(stream.isEof).thenReturn(isEof)

            val decoder = ReplacementCipherDecoder(mock(), stream)

            assertEquals(isEof, decoder.isEof)
        }

        testEof(true)
        testEof(false)
    }

    @Test
    fun readAndDecodeOneByteFromStream() {
        val stream: IInputStream = mock()
        val byteToDecode = Byte.MAX_VALUE
        whenever(stream.read()).thenReturn(byteToDecode)

        val decodeResult = Byte.MIN_VALUE
        val permutation: ICodec<Byte> = mock()
        whenever(permutation.decode(any())).thenReturn(decodeResult)

        val decoder = ReplacementCipherDecoder(permutation, stream)

        assertEquals(decodeResult, decoder.read())

        verify(stream).read()
        verify(permutation).decode(anyVararg())
    }

    @Test
    fun readAndDecodeListOfBytesFromStream() {
        val stream: IInputStream = mock()
        val byteToDecode = Byte.MAX_VALUE
        whenever(stream.read()).thenReturn(byteToDecode)

        val decodeResult = Byte.MIN_VALUE
        val permutation: ICodec<Byte> = mock()
        whenever(permutation.decode(any())).thenReturn(decodeResult)

        val decoder = ReplacementCipherDecoder(permutation, stream)
        val resultBytes = MutableList(0) { decodeResult }
        val bytesCount = 10000

        decoder.read(resultBytes, bytesCount)

        assertEquals(bytesCount, resultBytes.size)
        resultBytes.forEach { decodedByte ->
            assertEquals(decodeResult, decodedByte)
        }

        verify(stream, times(bytesCount)).read()
        verify(permutation, times(bytesCount)).decode(anyVararg())
    }
}
