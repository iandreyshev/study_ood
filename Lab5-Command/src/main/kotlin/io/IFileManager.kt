package io

interface IFileManager : IImageFileManager {
    fun saveTo(path: String, text: String, extension: String)
}
