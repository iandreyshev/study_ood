package io

import java.io.File

class FileManager(rootPath: String) : IFileManager {
    companion object {
        private const val INDEX_FILE = "index.html"
        private const val IMAGES_DIR = "images"
    }

    private var mRoot: File = File(rootPath)
    private var mImagesDir: File
    private val mImages = HashMap<String, File>()
    private val mImagesToDelete = HashSet<String>()

    init {
        mRoot.mkdirs()

        mImagesDir = File(mRoot, IMAGES_DIR)
        mImagesDir.mkdirs()
    }

    override fun saveTo(path: String, text: String) {
        val dirToSave = File(path)
        dirToSave.deleteRecursively()
        dirToSave.mkdirs()

        if (!dirToSave.exists() || !dirToSave.isDirectory) {
            throw IllegalArgumentException("Invalid directory '${dirToSave.absolutePath}' to save")
        }

        val indexFile = File(dirToSave, INDEX_FILE)
        indexFile.createNewFile()
        indexFile.outputStream().use { stream ->
            stream.write(text.toByteArray())
        }

        val imagesDir = File(dirToSave, IMAGES_DIR)
        imagesDir.mkdirs()
        mImages.forEach { id, image ->
            if (!mImagesToDelete.contains(id)) {
                val target = File(imagesDir, image.name)
                image.copyTo(target, overwrite = true)
            }
        }
    }

    override fun copyImage(path: String): String? {
        return try {
            val image = File(path)
            val imageInStore = File(mImagesDir, image.name)
            imageInStore.createNewFile()
            imageInStore.writeBytes(image.readBytes())
            val newPath = imageInStore.toRelativeString(mRoot)
                    .replace('\\', '/')
            mImages[newPath] = imageInStore
            newPath
        } catch (ex: Exception) {
            null
        }
    }

    override fun markImageOnDelete(imagePath: String, isOnDelete: Boolean) {
        if (!mImages.containsKey(imagePath)) {
            return
        }

        if (isOnDelete) {
            mImagesToDelete.add(imagePath)
        } else {
            mImagesToDelete.remove(imagePath)
        }
    }

    override fun deleteImage(imagePath: String) {
        val image = mImages[imagePath]
        image?.deleteRecursively()
        mImages.remove(imagePath)
    }

    fun clear() {
        mRoot.deleteRecursively()
    }
}
