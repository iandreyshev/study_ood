package compressor

import inputStream.IInputStream

class RleDecompressor(private val stream: IInputStream) : IInputStream {
    private val mRleChunk: RleCache = RleCache()

    override val isEof: Boolean
        get() = stream.isEof && mRleChunk.isEmpty

    override fun read(): Byte {
        if (mRleChunk.isEmpty) {
            resetCache()
        }

        --mRleChunk.count

        return mRleChunk.byte
    }

    private fun resetCache() {
        mRleChunk.count = stream.read()
        mRleChunk.byte = stream.read()
    }
}
