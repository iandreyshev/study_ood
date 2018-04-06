package serializer

import document.IParagraph
import org.apache.commons.text.StringEscapeUtils

class XMLSerializer : DocumentSerializer() {
    private var mTitle = ""
    private var mItems = ""
    override val extension: String = "xml"

    override fun onSetTitle(title: String) {
        mTitle = "\n  <title text=\"${title.escapeXML}\" />"
    }

    override fun onInsertParagraph(paragraph: IParagraph) {
        mItems += "\n  <paragraph text=\"${paragraph.text.escapeXML}\" />"
    }

    override fun serialize(): String {
        return """
<?xml version="1.0"?>
<document>$mTitle$mItems
</document>""".trimIndent()
    }

    private val String.escapeXML
        get() = StringEscapeUtils.escapeXml10(this)
}
