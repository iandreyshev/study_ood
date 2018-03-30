package document

class Paragraph(override var text: String) : IParagraph {
    override fun toString(): String {
        return "<p>$text</p>"
    }
}
