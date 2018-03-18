package cipher

import inputStream.IInputStream
import inputStream.MemoryInputStream
import junit.framework.TestCase.assertEquals
import org.junit.Test
import outputStream.IOutputStream
import outputStream.MemoryOutputStream
import randByte
import randLong

class CipherTest {

    @Test
    fun decodeAfterEncodingWithSamePermutationReturnSourceBytes() {
        val bytesCount = 10000
        val bytes = ByteArray(bytesCount) { randByte(it + 1) }
        val permutationsKeys = LongArray(100) {
            randLong(it + 1)
        }
        val memoryToWrite = MutableList(0) { it.toByte() }
        val memoryToRead = MutableList(0) { it.toByte() }

        var encoder: IOutputStream = MemoryOutputStream(memoryToWrite)
        permutationsKeys.forEach { encodeKey ->
            val permutation = ShuffleCodec(encodeKey)
            encoder = ReplacementCipherEncoder(permutation, encoder)
        }
        encoder.write(bytes, bytesCount)

        var decoder: IInputStream = MemoryInputStream(memoryToWrite)
        permutationsKeys.forEach { decodeKey ->
            val permutation = ShuffleCodec(decodeKey)
            decoder = ReplacementCipherDecoder(permutation, decoder)
        }
        decoder.read(memoryToRead, bytesCount)

        memoryToRead.forEachIndexed { index, byte ->
            assertEquals(byte, bytes[index])
        }
    }
}
