package compressor

interface ICompressor {
    fun compress(bytes: ByteArray): ByteArray

    fun decompress(bytes: ByteArray): ByteArray
}
