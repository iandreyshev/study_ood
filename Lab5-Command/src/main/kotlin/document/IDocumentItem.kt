package document

interface IDocumentItem {
    companion object {
        fun newParagraph(paragraph: IParagraph) = object : IDocumentItem {
            override val paragraph: IParagraph? = paragraph
            override val image: IImage? = null

            override fun toString(): String {
                return paragraph.toString()
            }
        }

        fun newImage(image: IImage) = object : IDocumentItem {
            override val image: IImage? = image
            override val paragraph: IParagraph? = null

            override fun toString(): String {
                return image.toString()
            }
        }
    }

    val paragraph: IParagraph?

    val image: IImage?
}
