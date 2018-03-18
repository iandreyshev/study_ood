package document

interface IImage {
    val path: String

    val width: Int

    val height: Int

    fun resize(newWidth: Int, newHeight: Int)
}
