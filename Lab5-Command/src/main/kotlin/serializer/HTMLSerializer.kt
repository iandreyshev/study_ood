package serializer

import document.IImage
import document.IParagraph
import org.apache.commons.text.StringEscapeUtils

class HTMLSerializer : DocumentSerializer() {
    override val extension: String = "html"

    private var mTitle = ""
    private var mItems = ""

    override fun onSetTitle(title: String) {
        if (!title.isEmpty()) {
            mTitle = "\n        <h1>${title.escapeHTML}</h1>"
        }
    }

    override fun onInsertParagraph(paragraph: IParagraph) {
        mItems += "\n        <p>${paragraph.text.escapeHTML}</p>"
    }

    override fun onInsertImage(image: IImage) = with(image) {
        mItems += "\n        <img src=\"${path.escapeHTML}\" height=\"$height\" width=\"$width\">"
    }

    override fun onSerialize(): ByteArray {
        return """
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
    </head>
    <body>$mTitle$mItems
    </body>
</html>""".toByteArray()
    }

    private val String.escapeHTML
        get() = StringEscapeUtils.escapeHtml4(this)
}
