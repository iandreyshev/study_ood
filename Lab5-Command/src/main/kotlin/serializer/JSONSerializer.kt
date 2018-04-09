package serializer

import document.IImage
import document.IParagraph
import org.apache.commons.text.StringEscapeUtils

class JSONSerializer : DocumentSerializer() {
    override val extension: String = "json"

    private var mTitle = ""
    private var mElements: MutableList<String> = mutableListOf()

    override fun onSetTitle(title: String) {
        if (!title.isEmpty()) {
            mTitle = "    \"title\": \"${title.escapeJSON}\""
        }
    }

    override fun onInsertParagraph(paragraph: IParagraph) {
        mElements.add(
"""    "paragraph": {
        "text": "${paragraph.text.escapeJSON}"
    }""")
    }

    override fun onInsertImage(image: IImage) {
        mElements.add(
"""    "image": {
        "path": "${image.path.escapeJSON}",
        "width": ${image.width},
        "height": ${image.height}
    }""")
    }

    override fun serialize(): String {
        var result = "{"

        if (!mTitle.isEmpty() || !mElements.isEmpty()) {
            result += "\n"
        }

        result += mTitle

        if (!mTitle.isEmpty() && !mElements.isEmpty()) {
            result += ",\n"
        }

        result += mElements.joinToString(separator = ",\n")
        if (result.length > 1) result += "\n"

        return "$result}\n"
    }

    private val String.escapeJSON
        get() = StringEscapeUtils.unescapeJson(this)
}
