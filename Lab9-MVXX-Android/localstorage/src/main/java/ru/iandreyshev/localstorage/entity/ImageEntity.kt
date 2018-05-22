package ru.iandreyshev.localstorage.entity

import android.graphics.Bitmap
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne
import ru.iandreyshev.localstorage.converter.BitmapConverter

@Entity
internal class ImageEntity(
        @Id
        var id: Long = 0,
        var x: Float = 0f,
        var y: Float = 0f,
        var width: Float = 0f,
        var height: Float = 0f,
        @Convert(converter = BitmapConverter::class, dbType = ByteArray::class)
        var image: Bitmap? = null
) {

    lateinit var canvas: ToOne<CanvasEntity>

}
