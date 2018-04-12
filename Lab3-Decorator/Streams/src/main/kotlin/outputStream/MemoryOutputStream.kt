package outputStream

class MemoryOutputStream(private val mMemory: MutableCollection<Byte>) : IOutputStream {
    override fun write(byte: Byte) {
        mMemory.add(byte)
    }

    override fun close() {
        // Nothing to do
    }
}
