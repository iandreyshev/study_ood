package compressor

import java.util.zip.Deflater
import java.util.zip.Inflater
import java.io.ByteArrayOutputStream

class SimpleCompressor : ICompressor {
    private val mCompressor = Deflater(Deflater.BEST_COMPRESSION)
    private val mDecompressor = Inflater()

    override fun compress(bytes: ByteArray): ByteArray {
        val buffer = ByteArray(1024)
        val resultBytes = ByteArrayOutputStream()
        mCompressor.setInput(bytes)
        mCompressor.finish()

        while (!mCompressor.finished()) {
            val bufferSize = mCompressor.deflate(buffer)
            resultBytes.write(buffer, 0, bufferSize)
        }

        mCompressor.end()

        return resultBytes.toByteArray()
    }

    override fun decompress(bytes: ByteArray): ByteArray {
        val buffer = ByteArray(1024)
        val resultBytes = ByteArrayOutputStream()
        mDecompressor.setInput(bytes)

        while (!mDecompressor.finished()) {
            val bufferSize = mDecompressor.inflate(buffer)
            resultBytes.write(buffer, 0, bufferSize)
        }

        mDecompressor.end()

        return resultBytes.toByteArray()
    }
}
