package ru.iandreyshev.adobeKiller.data

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity data class DbCanvas(
        @Id var id: Long = 0,
        var name: String
)
