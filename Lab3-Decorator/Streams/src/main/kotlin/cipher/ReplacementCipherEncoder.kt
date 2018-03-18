package cipher

import outputStream.IOutputStream
import outputStream.OutputDecorator

class ReplacementCipherEncoder(
        private val mPermutation: ICodec<Byte>,
        stream: IOutputStream
) : OutputDecorator(stream) {
    override fun transformByte(byte: Byte) = mPermutation.encode(byte)
}
