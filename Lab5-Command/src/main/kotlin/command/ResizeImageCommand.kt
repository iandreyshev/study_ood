package command

class ResizeImageCommand(
        private var oldWidth: Int,
        private var oldHeight: Int,
        private val newWidth: Int,
        private val newHeight: Int,
        private val onResizeListener: (w: Int, h: Int) -> Unit
) : Command() {
    override fun onExecute() = onResizeListener(newWidth, newHeight)

    override fun onUndo() = onResizeListener(oldWidth, oldHeight)
}
