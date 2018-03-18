package io

import document.IDocument

class DocumentInterpreter : IDocumentActionParser {
    override fun apply(document: IDocument, command: String): Boolean {
        return true
    }
}
