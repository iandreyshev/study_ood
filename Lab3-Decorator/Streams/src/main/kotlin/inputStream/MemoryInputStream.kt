package inputStream

class MemoryInputStream(memory: Collection<Byte>) : IInputStream {
    private var mIterator: Iterator<Byte> = memory.iterator()

    override val isEof: Boolean
        get() = !mIterator.hasNext()

    override fun read(): Byte = mIterator.next()
}
