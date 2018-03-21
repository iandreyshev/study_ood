package io

interface IDocumentActionParser {
    fun apply(command: String): Boolean
}
