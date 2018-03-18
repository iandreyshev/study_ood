package cipher

interface ICodec<T> {
    fun encode(byte: T): T

    fun decode(byte: T): T
}
