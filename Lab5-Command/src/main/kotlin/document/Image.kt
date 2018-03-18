package document

class Image(
        override val path: String,
        width: Int,
        height: Int
) : IImage {
    override var width: Int = width
        private set
    override var height: Int = height
        private set

    override fun resize(newWidth: Int, newHeight: Int) {
        width = newWidth
        height = newHeight
    }

    override val asTag: String
        get() = "<img src=\"$path\" height=\"$height\" width=\"$width\">"
}
