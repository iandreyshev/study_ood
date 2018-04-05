package document

import command.ICommandQueue
import command.ResizeImageCommand

class Image(
        private val queue: ICommandQueue,
        override val path: String,
        override var width: Int,
        override var height: Int
) : IImage {
    override fun resize(newWidth: Int, newHeight: Int) {
        val command = ResizeImageCommand(width, height, newWidth, newHeight) { width, height ->
            this.width = width
            this.height = height
        }
        queue.apply(command)
    }

    override fun toString(): String {
        return "<img src=\"$path\" height=\"$height\" width=\"$width\">"
    }
}
