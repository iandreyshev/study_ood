package cipher

import junit.framework.TestCase.*
import org.junit.Test
import java.util.*

class BytePermutationTest {

    companion object {
        private val ALL_BYTES = (Byte.MIN_VALUE..Byte.MAX_VALUE)
                .map { it.toByte() }
                .toList()
    }

    @Test
    fun haveOneValueForAllBytes() {
        fun isContainByte(transform: (Byte) -> Byte) {
            val passedBytes = HashSet<Byte>()

            ALL_BYTES.forEach { byte ->
                val code = transform(byte)

                if (passedBytes.contains(code)) {
                    fail()
                }

                passedBytes.add(code)
            }

            ALL_BYTES.forEach { byte ->
                assertTrue(passedBytes.contains(byte))
            }
        }

        isContainByte(ShuffleCodec(0)::encode)
        isContainByte(ShuffleCodec(0)::decode)
    }

    @Test
    fun decodeAfterEncodeReturnSourceByte() {
        fun checkEncodeDecode(key: Long, repeatsCount: Int) {
            val permutation = ShuffleCodec(key)

            ALL_BYTES.forEach { sourceByte ->
                var encoded = sourceByte

                repeat(repeatsCount) {
                    encoded = permutation.encode(encoded)
                }

                var decoded = encoded

                repeat(repeatsCount) {
                    decoded = permutation.decode(decoded)
                }

                assertEquals(sourceByte, decoded)
            }
        }

        val key = Random(Calendar.getInstance().timeInMillis).nextLong()
        checkEncodeDecode(key, 1000)
    }
}
