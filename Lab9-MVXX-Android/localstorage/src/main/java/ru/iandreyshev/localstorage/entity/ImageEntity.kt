package ru.iandreyshev.localstorage.entity

import io.objectbox.annotation.Backlink
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

@Entity
internal class ImageEntity(
        @Id
        var id: Long = 0,
        var x: Float = 0f,
        var y: Float = 0f,
        var width: Float = 0f,
        var height: Float = 0f,
        var imagePath: String
) {

    @Backlink(to = "images")
    lateinit var canvas: ToOne<CanvasEntity>

}
