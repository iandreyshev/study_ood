package cipher

import inputStream.IInputStream
import inputStream.InputDecorator

class ReplacementCipherDecoder(
        private val mPermutation: ICodec<Byte>,
        stream: IInputStream
) : InputDecorator(stream) {
    override fun transformByte(byte: Byte) = mPermutation.decode(byte)
}
