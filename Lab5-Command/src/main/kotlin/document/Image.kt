package document

import command.Command
import command.ICommandQueue

class Image(
        private val queue: ICommandQueue,
        override val path: String,
        override var width: Int,
        override var height: Int
) : IImage {
    override fun resize(newWidth: Int, newHeight: Int) {
        queue.apply(ResizeImageCommand(newWidth, newHeight))
    }

    override fun toString(): String {
        return "<img src=\"$path\" height=\"$height\" width=\"$width\">"
    }

    inner class ResizeImageCommand(
            private val newWidth: Int,
            private val newHeight: Int
    ) : Command() {
        private var mOldWidth: Int = width
        private var mOldHeight: Int = height

        override fun onExecute() {
            mOldWidth = width
            mOldHeight = height
            width = newWidth
            height = newHeight
        }

        override fun onUndo() {
            width = mOldWidth
            height = mOldHeight
        }
    }
}
