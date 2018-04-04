package compressor

import outputStream.IOutputStream
import outputStream.OutputDecorator2
import java.io.ByteArrayOutputStream
import java.util.zip.ZipOutputStream

class StreamCompressor(stream: IOutputStream) : OutputDecorator2(stream) {
    private val mOutput = ByteArrayOutputStream()
    private val mStream = ZipOutputStream(mOutput)

    override fun transformByte(byte: Byte): Byte {
        mStream.write(byte.toInt())
        mOutput.
        return byte
    }

    override fun transformSeveralBytes(data: ByteArray, dataSize: Int): ByteArray {
        return data
    }
}
