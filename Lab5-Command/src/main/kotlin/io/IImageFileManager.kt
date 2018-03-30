package io

interface IImageFileManager {
    fun copyImage(path: String): String?

    fun markImageOnDelete(imagePath: String, isOnDelete: Boolean)

    fun deleteImage(imagePath: String)
}
