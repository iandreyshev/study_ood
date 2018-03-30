package command

import document.IDocumentItem

class ResizeImageCommand(
        private val items: MutableList<IDocumentItem>,
        private val position: Int,
        private val newWidth: Int,
        private val newHeight: Int
) : ICommand {
    private val mOldWidth: Int = items.getOrNull(position)?.image?.width
            ?: throw IllegalArgumentException()
    private val mOldHeight: Int = items.getOrNull(position)?.image?.height
            ?: throw IllegalArgumentException()

    override fun execute() {
        items[position].image?.width = newWidth
        items[position].image?.height = newHeight
    }

    override fun undo() {
        items[position].image?.width = mOldWidth
        items[position].image?.height = mOldHeight
    }
}
