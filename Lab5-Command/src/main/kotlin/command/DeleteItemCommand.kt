package command

import document.IDocumentItem
import io.IImageFileManager

class DeleteItemCommand(
        private val fileManager: IImageFileManager,
        private val items: MutableList<IDocumentItem>,
        private val position: Int
) : ICommand {
    private val item = items[position]

    override fun execute() {
    }

    override fun undo() {
    }

    override fun destroy() {
    }
}
