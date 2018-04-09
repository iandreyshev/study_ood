package serializer

import document.IImage
import document.IParagraph
import org.apache.commons.text.StringEscapeUtils

class HTMLSerializer : DocumentSerializer() {
    private var mTitle = ""
    private var mItems = ""
    override val extension: String = "html"

    override fun onSetTitle(title: String) {
        mTitle = "\n        <h1>${title.escapeHTML}</h1>"
    }

    override fun onInsertParagraph(paragraph: IParagraph) {
        mItems += "\n        <p>${paragraph.text.escapeHTML}</p>"
    }

    override fun onInsertImage(image: IImage) = with(image) {
        mItems += "\n        <img src=\"${path.escapeHTML}\" height=\"$height\" width=\"$width\">"
    }

    override fun serialize(): String {
        return """
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
    </head>
    <body>$mTitle$mItems
    </body>
</html>"""
    }

    private val String.escapeHTML
        get() = StringEscapeUtils.escapeHtml4(this)
}
