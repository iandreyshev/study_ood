package document

class Image(
        override val path: String,
        override var width: Int,
        override var height: Int
) : IImage {
    override fun resize(newWidth: Int, newHeight: Int) {
        width = newWidth
        height = newHeight
    }

    override fun toString(): String {
        return "<img src=\"$path\" height=\"$height\" width=\"$width\">"
    }
}
