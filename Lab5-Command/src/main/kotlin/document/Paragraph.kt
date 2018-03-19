package document

class Paragraph(override var text: String) : IParagraph {
    val asTag: String
        get() = "<p>$text</p>"
}
