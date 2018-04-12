package compressor

import outputStream.IOutputStream

class RleCompressor(private val stream: IOutputStream) : IOutputStream {
    private val mCache: RleCache = RleCache()

    override fun write(byte: Byte) {
        when {
            mCache.isEmpty -> {
                mCache.start(byte)
            }
            mCache.isThisByte(byte) -> {
                if (mCache.isFull) {
                    flush()
                }
                mCache.byte = byte
                mCache.count++
            }
            else -> {
                flush()
                mCache.start(byte)
            }
        }
    }

    private fun flush() {
        if (mCache.isEmpty) {
            return
        }

        stream.write(mCache.count)
        stream.write(mCache.byte)

        mCache.count = 0
    }

    override fun close() = flush()
}
