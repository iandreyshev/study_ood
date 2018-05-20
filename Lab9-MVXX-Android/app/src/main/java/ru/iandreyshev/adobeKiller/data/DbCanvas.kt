package ru.iandreyshev.adobeKiller.data

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany

@Entity
class DbCanvas(
        @Id var id: Long = 0,
        var name: String
) {
    lateinit var shapes: ToMany<DbShape>
}
