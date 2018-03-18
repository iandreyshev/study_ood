package cipher

import java.util.*

class ShuffleCodec(key: Long) : ICodec<Byte> {

    private val mDecodeTable = (Byte.MIN_VALUE..Byte.MAX_VALUE)
            .map { it.toByte() }
            .shuffled(Random(key))
            .toByteArray()
    private val mEncodeTable = ByteArray(mDecodeTable.size)

    init {
        mDecodeTable.forEachIndexed { index, byte ->
            mEncodeTable[byte.asIndex] = (index + Byte.MIN_VALUE).toByte()
        }
    }

    override fun encode(byte: Byte) = mEncodeTable[byte.asIndex]

    override fun decode(byte: Byte) = mDecodeTable[byte.asIndex]

    private val Byte.asIndex
        get() = toInt() - Byte.MIN_VALUE
}
