package compressor

import inputStream.IInputStream
import inputStream.InputDecorator2

class StreamDecompressor(stream: IInputStream) : InputDecorator2(stream) {
    override fun transformByte(byte: Byte): Byte {
        return byte
    }

    override fun transformSeveralBytes(dstData: MutableCollection<Byte>) {
    }
}
