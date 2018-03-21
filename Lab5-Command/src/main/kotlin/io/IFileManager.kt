package io

interface IFileManager {
    fun copyImage(path: String): Long?

    @Throws(IllegalArgumentException::class)
    fun getRelativePath(imageId: Long): String

    fun markImageOnDelete(imageId: Long, isOnDelete: Boolean)

    fun deleteImage(imageId: Long)
}
