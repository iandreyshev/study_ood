package compressor

import inputStream.IInputStream

class RleDecompressor(private val stream: IInputStream) : IInputStream {
    private val mCache: RleCache = RleCache()

    override val isEof: Boolean
        get() = stream.isEof && mCache.isEmpty

    override fun read(): Byte {
        if (mCache.isEmpty) {
            resetCache()
        }

        --mCache.count

        return mCache.byte
    }

    private fun resetCache() {
        mCache.count = stream.read()
        mCache.byte = stream.read()
    }
}
