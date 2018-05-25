package ru.iandreyshev.localstorage.entity

import io.objectbox.annotation.Backlink
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany

@Entity
internal class CanvasEntity(
        @Id
        var id: Long = 0,
        var name: String = ""
) {

    @Backlink(to = "canvas")
    lateinit var shapes: ToMany<ShapeEntity>
    @Backlink(to = "canvas")
    lateinit var images: ToMany<ImageEntity>

}
