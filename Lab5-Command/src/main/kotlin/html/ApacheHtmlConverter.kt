package html

import org.apache.commons.text.StringEscapeUtils

class ApacheHtmlConverter : IHtmlConverter {
    override fun transform(source: String): String = StringEscapeUtils.escapeHtml4(source)
}
