package document.factory

import document.IDocumentItem
import document.IImage
import document.IParagraph

class ItemsFactory : IItemsFactory {
    override fun create(paragraph: IParagraph): IDocumentItem {
        return object : IDocumentItem {
            override val paragraph: IParagraph? = paragraph
            override val image: IImage? = null

            override fun toString(): String {
                return paragraph.toString()
            }
        }
    }

    override fun create(image: IImage): IDocumentItem {
        return object : IDocumentItem {
            override val image: IImage? = image
            override val paragraph: IParagraph? = null

            override fun toString(): String {
                return image.toString()
            }
        }
    }
}
