package io

import java.io.File

class FileManager(rootPath: String) : IFileManager {
    companion object {
        private const val INDEX_FILE = "index.html"
        private const val IMAGES_DIR = "images"
    }

    private var mRoot: File = File(rootPath)
    private var mIndex: File
    private var mImagesDir: File
    private var mLastId = 1L
    private val mImages = HashMap<Long, File>()
    private val mImagesToDelete = HashSet<Long>()

    init {
        mRoot.mkdirs()

        mIndex = File(mRoot, INDEX_FILE)
        mIndex.createNewFile()

        mImagesDir = File(mRoot, IMAGES_DIR)
        mImagesDir.mkdirs()
    }

    override fun copyImage(path: String): Long? {
        return try {
            val image = File(path)
            val imageInStore = File(mImagesDir, image.name)
            imageInStore.createNewFile()
            imageInStore.writeBytes(image.readBytes())
            mImages[mLastId] = imageInStore
            ++mLastId
        } catch (ex: Exception) {
            null
        }
    }

    @Throws(IllegalArgumentException::class)
    override fun getRelativePath(imageId: Long): String {
        val image = mImages[imageId]
        return image?.toRelativeString(mRoot) ?: throw IllegalArgumentException()
    }

    override fun markImageOnDelete(imageId: Long, isOnDelete: Boolean) {
        if (mImages.containsKey(imageId)) {
            mImagesToDelete.add(imageId)
        }
    }

    override fun deleteImage(imageId: Long) {
        val image = mImages[imageId]
        image?.deleteRecursively()
    }

    fun clear() {
        mRoot.deleteRecursively()
    }
}
