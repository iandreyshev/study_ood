package compressor

class RleCache(
        var count: Byte = 0,
        var byte: Byte = 0) {
    val isEmpty: Boolean
        get() = (count == 0.toByte())
    val isFull: Boolean
        get() = (count == Byte.MAX_VALUE)

    fun isThisByte(byte: Byte): Boolean {
        return this.byte == byte
    }

    fun start(byte: Byte) {
        count = 1
        this.byte = byte
    }
}
