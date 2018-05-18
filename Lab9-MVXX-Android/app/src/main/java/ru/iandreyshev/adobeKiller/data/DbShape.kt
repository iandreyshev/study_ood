package ru.iandreyshev.adobeKiller.data

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class DbShape(
        @Id var id: Long,
        var canvasId: Long
)
