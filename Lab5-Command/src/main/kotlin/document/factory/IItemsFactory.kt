package document.factory

import document.IDocumentItem
import document.IImage
import document.IParagraph

interface IItemsFactory {
    fun create(paragraph: IParagraph): IDocumentItem

    fun create(image: IImage): IDocumentItem
}
