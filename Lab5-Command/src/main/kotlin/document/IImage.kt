package document

interface IImage {
    val path: String

    var width: Int

    var height: Int

    fun resize(newWidth: Int, newHeight: Int)
}
