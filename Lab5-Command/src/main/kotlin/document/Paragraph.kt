package document

class Paragraph(override var text: String) : IParagraph {
    override val asTag: String
        get() = "<p>$text</p>"
}
