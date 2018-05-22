package ru.iandreyshev.localstorage.converter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import io.objectbox.converter.PropertyConverter
import java.io.ByteArrayOutputStream

internal class BitmapConverter : PropertyConverter<Bitmap?, ByteArray?> {

    companion object {
        private const val QUALITY = 100
        private val COMPRESS_FORMAT = Bitmap.CompressFormat.JPEG
    }

    override fun convertToEntityProperty(databaseValue: ByteArray?): Bitmap? {
        databaseValue ?: return null
        return BitmapFactory.decodeByteArray(databaseValue, 0, databaseValue.size)
    }

    override fun convertToDatabaseValue(entityProperty: Bitmap?): ByteArray? {
        entityProperty ?: return null
        return ByteArrayOutputStream().use { stream ->
            entityProperty.compress(COMPRESS_FORMAT, QUALITY, stream)
            return@use stream.toByteArray()
        }
    }
}