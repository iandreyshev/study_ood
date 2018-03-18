package io

import document.IDocument

interface IDocumentActionParser {
    fun apply(document: IDocument, command: String): Boolean
}
